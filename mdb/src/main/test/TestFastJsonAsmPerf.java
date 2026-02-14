import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.mdb.collection.ConcurrentArrayDequeX;
import com.mdb.collection.ConcurrentArrayListX;
import com.mdb.collection.ConcurrentCopyAndWriteListX;
import com.mdb.collection.TrackedList;
import com.mdb.collection.TrackedLockList;
import com.mdb.collection.TrackedQueue;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;

/**
 * Compare Fastjson perf with asm on/off under nested tracked collections.
 */
public class TestFastJsonAsmPerf {
    private static final int ELEMENT_COUNT = 256;
    private static final int WARMUP_ROUNDS = 15_000;
    private static final int MEASURE_ROUNDS = 80_000;

    private static final ObjectSerializer TRACKED_COLLECTION_SNAPSHOT_SERIALIZER = new ObjectSerializer() {
        @Override
        public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
            if (object == null) {
                serializer.writeNull();
                return;
            }
            if (object instanceof Collection) {
                serializer.write(new ArrayList<>((Collection<?>) object));
                return;
            }
            serializer.write(object);
        }
    };

    private static final PropertyFilter PROFILTER = new PropertyFilter() {
        @Override
        public boolean apply(Object object, String name, Object param) {
            if (param instanceof Integer) {
                return ((Integer) param).intValue() != 0;
            } else if (param instanceof Long) {
                return ((Long) param).longValue() != 0L;
            } else if (param instanceof Collection) {
                return !((Collection<?>) param).isEmpty();
            }
            return true;
        }
    };

    static class BenchBag {
        private final ConcurrentCopyAndWriteListX<Integer> longListCow = new ConcurrentCopyAndWriteListX<>();
        private final ConcurrentArrayListX<Integer> longListRw = new ConcurrentArrayListX<>();
        private final ConcurrentArrayDequeX<Integer> longQueue = new ConcurrentArrayDequeX<>();

        public ConcurrentCopyAndWriteListX<Integer> getLongListCow() {
            return longListCow;
        }

        public ConcurrentArrayListX<Integer> getLongListRw() {
            return longListRw;
        }

        public ConcurrentArrayDequeX<Integer> getLongQueue() {
            return longQueue;
        }
    }

    public static void main(String[] args) {
        BenchBag bag = buildBag(ELEMENT_COUNT);
        SerializeConfig asmOn = buildConfig(true);
        SerializeConfig asmOff = buildConfig(false);

        // warmup both configs
        runRounds(bag, asmOn, WARMUP_ROUNDS);
        runRounds(bag, asmOff, WARMUP_ROUNDS);

        Result on = runRounds(bag, asmOn, MEASURE_ROUNDS);
        Result off = runRounds(bag, asmOff, MEASURE_ROUNDS);

        double slowdownPct = ((double) off.nanos / (double) on.nanos - 1.0) * 100.0;

        System.out.println("=== FastJson ASM Perf ===");
        System.out.println("payloadSizes listCow=" + bag.getLongListCow().size()
                + " listRw=" + bag.getLongListRw().size()
                + " queue=" + bag.getLongQueue().size());
        System.out.println("rounds warmup=" + WARMUP_ROUNDS + " measure=" + MEASURE_ROUNDS);
        System.out.println("asmOn  : " + on.format());
        System.out.println("asmOff : " + off.format());
        System.out.println(String.format("slowdown(asmOff vs asmOn)=%.2f%%", slowdownPct));
    }

    private static BenchBag buildBag(int n) {
        BenchBag bag = new BenchBag();
        for (int i = 1; i <= n; i++) {
            bag.getLongListCow().add(i);
            bag.getLongListRw().add(i);
            bag.getLongQueue().add(i);
        }
        return bag;
    }

    private static SerializeConfig buildConfig(boolean asmEnable) {
        SerializeConfig config = new SerializeConfig();
        config.setAsmEnable(asmEnable);

        config.put(Collection.class, TRACKED_COLLECTION_SNAPSHOT_SERIALIZER);
        config.put(List.class, TRACKED_COLLECTION_SNAPSHOT_SERIALIZER);
        config.put(Queue.class, TRACKED_COLLECTION_SNAPSHOT_SERIALIZER);

        config.put(TrackedList.class, TRACKED_COLLECTION_SNAPSHOT_SERIALIZER);
        config.put(TrackedLockList.class, TRACKED_COLLECTION_SNAPSHOT_SERIALIZER);
        config.put(TrackedQueue.class, TRACKED_COLLECTION_SNAPSHOT_SERIALIZER);

        config.put(ConcurrentCopyAndWriteListX.class, TRACKED_COLLECTION_SNAPSHOT_SERIALIZER);
        config.put(ConcurrentArrayListX.class, TRACKED_COLLECTION_SNAPSHOT_SERIALIZER);
        config.put(ConcurrentArrayDequeX.class, TRACKED_COLLECTION_SNAPSHOT_SERIALIZER);
        return config;
    }

    private static Result runRounds(BenchBag bag, SerializeConfig config, int rounds) {
        long checksum = 0;
        long begin = System.nanoTime();
        for (int i = 0; i < rounds; i++) {
            String json = JSON.toJSONString(bag, config, PROFILTER, SerializerFeature.WriteNonStringKeyAsString);
            checksum += json.length();
        }
        long end = System.nanoTime();
        return new Result(end - begin, rounds, checksum);
    }

    static class Result {
        final long nanos;
        final int rounds;
        final long checksum;

        Result(long nanos, int rounds, long checksum) {
            this.nanos = nanos;
            this.rounds = rounds;
            this.checksum = checksum;
        }

        String format() {
            double ms = nanos / 1_000_000.0;
            double qps = rounds * 1_000_000_000.0 / nanos;
            return String.format("time=%.2f ms, qps=%.2f, checksum=%d", ms, qps, checksum);
        }
    }
}
