import java.io.File;

public class DelFiles {

	/**
	 * @param args
	 * @author yangl
	 * @date 2013-6-7 ионГ10:08:00
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DelFiles t = new DelFiles();
		File file = new File("E:\\HUNDSUNCPP\\bank\\szpa");
		t.chkFiles(file);
	}

	private void chkFiles(File file) {
		File[] f = file.listFiles();
		for (int i = 0; i < f.length; i++) {
//			System.out.println(f[i].getName());
			if (f[i].getName().equals(".svn")) {
				delFiles(f[i]);
			}else if(f[i].isDirectory()){
				chkFiles(f[i]);
			}
		}
	}

	private void delFiles(File file) {
		File[] f = file.listFiles();
		for (int i = 0; i < f.length; i++) {
			System.out.println(f[i]);
			if (f[i].isDirectory()) {
				delFiles(f[i]);
			} else {
				f[i].delete();
			}
		}
		file.delete();
	}
}
