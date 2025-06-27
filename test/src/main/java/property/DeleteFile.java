package property;

import java.io.File;

/**
 * @Auther: zhuxiaoqing
 * @Date: 2021/4/1 20:46
 * @Description:
 */
public class DeleteFile {

	private static DeleteFile ourInstance = new DeleteFile();

	public static DeleteFile getInstance() {
		return ourInstance;
	}

	String name = "_remote.repositories";

	public static void main(String[] args) {
		getInstance().delete("H:\\repository\\com\\sh\\engine");
	}

	public void delete(String path) {
		File files = new File(path);
		dir(files);
	}

	public void dir(File directory) {
		for (File file1 : directory.listFiles()) {
			if (file1.isDirectory()) {
				dir(file1);
			} else {
				if (file1.getName().equals(name)) {
					file1.delete();
					System.out.println(file1.getName());
				}
			}
		}
	}
}
