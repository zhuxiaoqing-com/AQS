import java.io.IOException;
import java.net.ServerSocket;

public class TestNet {
	public static void main(String[] args) throws IOException, InterruptedException {
		ServerSocket serverSocket = new ServerSocket(10101);
		Thread.sleep(1000_000);
	}
}
