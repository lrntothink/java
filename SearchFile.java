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
	private String allowFileType;
	private long totalFile = 0;
	private long actualSearchFiles = 0;
	PrintWriter   out   = null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SearchFile sf = new SearchFile();
		sf.doIt();
		
	}
	private void doIt(){
		//要搜索的内容
		String searchword = "杨亮";
		//要搜索的文件类型
		allowFileType = " txt ini js jsp java xml htm html properties sql ";
		//搜索的位置
		String searchPath = "D:\\workspace\\lcpt1.2";
		//搜索结果的存放位置
		String searchResultPath = "d:/112.txt";
		try {
			out   =   new PrintWriter(new OutputStreamWriter(new FileOutputStream(searchResultPath), "gbk"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long beginTime = System.currentTimeMillis();
		setSearchWord(searchword);
		searchFiles(new File(searchPath));
		long endTime = System.currentTimeMillis();
		System.out.println(searchPath+"路径下，共有文件："+totalFile+"个");
		System.out.println("实际搜索文件数："+actualSearchFiles+"个");
		System.out.println("共花费时间："+(endTime - beginTime)+" 毫秒");
		out.close();
	}
	
	private void searchFiles(File filePath ){
		if(filePath.isFile()){
			totalFile++;
			String filename = filePath.getName().toLowerCase();
			if(filename.indexOf(".")!=-1){
				String filetype = filename.substring(filename.lastIndexOf(".")+1).toLowerCase().trim();
				if(allowFileType.contains(" "+filetype+" ")){
					actualSearchFiles++;
					searchSingleFile(filePath,this.getSearchWord());
				}
			}
		}else if(filePath.isDirectory()){
			File[] file = filePath.listFiles();
			for(File f:file){
				searchFiles(f);
			}
		}
	}
	/**
	 * 在文件中搜索
	 * @param f 搜索的文件名
	 * @param searchWord 要搜索的内容
	 */
	private void searchSingleFile(File f,String searchWord) {
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
				out.println(f.getAbsolutePath()+"中总共 "+lineNums+" 行，共找到 "+totalNums+" 处\""+searchWord+"\"!");
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public String getSearchWord() {
		return searchWord;
	}
	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
}
