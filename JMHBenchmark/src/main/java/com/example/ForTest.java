package com.example;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.time.Instant;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class ForTest {

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(ForTest.class.getSimpleName())
                .forks(1)      // fork 1 次 JVM
                .warmupIterations(5)
                .measurementIterations(10)
                .build();

        new Runner(opt).run();
    }

    private static final int LOOP = 10_000_000; // 每次测试循环次数


    @Benchmark
    public void test01(Blackhole bh) {
        float sum1 = 0;
        float sum2 = 0;
        float sum3 = 0;
        float sum4 = 0;
        float sum5 = 0;
        int whileCount = 1_000_000_0;
        int i1 = whileCount / 5;
        for (float i = 0; i < i1; i++) {
            sum2 *= i;
        }
        for (float i = 0; i < i1; i++) {
            sum3 *= i;
        }
        for (float i = 0; i < i1; i++) {
            sum4 *= i;
        }
        for (float i = 0; i < i1; i++) {
            sum5 *= i;
        }
        for (float i = 0; i < i1; i++) {
            sum1 *= i;
        }
        float v = sum1 * sum2 * sum3 * sum4 * sum5;

        bh.consume(v);
    }

    @Benchmark
    public void test02(Blackhole bh) {
        float sum = 0;
        for (float i = 0; i < 1_000_000_0; i++) {
            sum *= i;
        }
        bh.consume(sum);
    }

    @Benchmark
    public void test03(Blackhole bh) {
        float sum1 = 0;
        float sum2 = 0;
        float sum3 = 0;
        float sum4 = 0;
        float sum5 = 0;
        int whileCount = 1_000_000_0;
        int i1 = whileCount / 5;
        for (float i = 0; i < whileCount; i += 5) {
            sum1 *= i;
            sum2 *= i + 1;
            sum3 *= i + 2;
            sum4 *= i + 3;
            sum5 *= i + 4;
        }
        float v = sum1 * sum2 * sum3 * sum4 * sum5;

        bh.consume(v);
    }

}
