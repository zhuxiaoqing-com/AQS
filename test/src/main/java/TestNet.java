import java.io.IOException;
import java.util.Random;

public class TestNet {
	public static void main(String[] args) throws IOException, InterruptedException {
		Random random = new Random();
		while (true) {
			int skillId = random.nextInt(100);
			if(skillId == 0) {
				break;
			}
		}
		System.out.println("success");
	}
}
