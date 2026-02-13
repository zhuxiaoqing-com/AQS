

import com.mdb.Mdb;
import com.mdb.util.XMLUtils;
import com.mdb.xmlgen.xmlparse.MdbMetadata;

import java.io.File;

public class TestFlush {

	public static void main(String[] args) throws Exception {
		Mdb.configLog();
		MdbMetadata mdb = new MdbMetadata();
		mdb.initialize(XMLUtils.getRootElement(TestMdb.inputXml));
		Mdb.getInstance().start(mdb);
		Thread.sleep(3000L);

		xbean.logic.Player p = table.logic.Player.get(2L);
		if (p == null) {
			p = new xbean.logic.Player();
			table.logic.Player.add(2L, p);
		}
		p.setLevel(1);
		p.setPlayerId(111L);
		Thread.sleep(1000L);
		Mdb.getInstance().flush(2L, null);

		System.err.println("回车退出");
		System.in.read();
		System.exit(-1);
	}
}
