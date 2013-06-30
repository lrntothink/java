import java.io.File;

public class test2 {

	/**
	 * @param args
	 * @author yangl
	 * @date 2013-6-7 ÉÏÎç10:08:00
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test2 t = new test2();
		File file = new File("C:/Documents and Settings/yl0806/×ÀÃæ/trans");
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
