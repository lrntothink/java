import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class SearchFile {
	private String searchWord;
	PrintWriter   out   = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SearchFile sf = new SearchFile();
		sf.doIt();
		
	}
	private void doIt(){
		try {
			out   =   new PrintWriter(new OutputStreamWriter(new FileOutputStream("d:/111.txt" ), "gbk"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String word = "double";
		File file = new File("D:\\workspace\\lcpt");
		setSearchWord(word);
		searchFiles(file);
		out.close();
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
	private void searchFiles(File filePath ){
		if(filePath.isFile()){
			searchSingleFile(filePath,this.getSearchWord());
		}else if(filePath.isDirectory()){
			File[] file = filePath.listFiles();
			for(File f:file){
				searchFiles(f);
			}
		}
	}
	private void searchSingleFile(File f,String searchWord) {
		String fileContents = "";
		try {
			FileInputStream file = new FileInputStream(f);
			BufferedReader in = new BufferedReader(new InputStreamReader(file,"gbk"));
			String str;
			int lineNums = 0;
			int totalNums = 0;
			while ((str = in.readLine()) != null) {
				lineNums++;
				if(str.contains(searchWord)){
					totalNums++;
					out.println("\t"+lineNums+"行：  "+str);
				}
			}
			if(totalNums!=0){
				out.println(f.getAbsolutePath()+"中总共"+lineNums+"行，共找到"+totalNums+"处\""+searchWord+"\"!");
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
