import com.mdb.Mdb;
import com.mdb.util.XMLUtils;
import com.mdb.xmlgen.xmlparse.MdbMetadata;

public class TestLru {
	public static void main(String[] args) throws Exception {
		Mdb.configLog();
		MdbMetadata mdb = new MdbMetadata();
		mdb.initialize(XMLUtils.getRootElement(TestMdb.inputXml));
		Mdb.getInstance().start(mdb);

		Thread.sleep(3000L);

		for (int i = 1; i <= 22; i++) { // 容量设置到21，加入22个元素，会根据 Math.min(size - capacity + 255, size) flush到最早最近没用的
			long k = (long)i;
			xbean.logic.Player p = table.logic.Player.get(k);
			if (p == null) {
				p = new xbean.logic.Player();
				p.setPlayerId(i);
				p.setLevel(i+1);
				table.logic.Player.add(k, p);
			}
			if(i % 2 ==0 && i != 22) {
				// 测试LRU，把2提到最近使用
				table.logic.Player.get(2L);
			}
		}
		System.err.println("回车退出");
		System.in.read();
		System.exit(-1);
	}
}
