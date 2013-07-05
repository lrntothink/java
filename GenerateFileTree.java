import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class GenerateFileTree {
	static PrintWriter   out ;
	private long loopNumbers = 0; 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			out   =   new PrintWriter(new OutputStreamWriter(new FileOutputStream("d:/111.txt" ), "gbk"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file = new File("D:\\Git");
		GenerateFileTree genfl = new GenerateFileTree();
		genfl.genFileTree(file);
		out.close();
	}
	
	private void genFileTree(File filePath ){
		out.println("├--"+filePath.getName());
		if(filePath.isDirectory()){
			loopNumbers++;
			File[] file = filePath.listFiles();
			for(File f:file){
				for(int i=0;i<loopNumbers;i++){
					out.print("│  ");
				}
				genFileTree(f);
			}
			loopNumbers--;
		}
	}
}
