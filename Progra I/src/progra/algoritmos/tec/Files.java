package progra.algoritmos.tec;
import java.io.File;
public class Files {
	String path="C:\\Users\\User1\\git\\Progra-1-Algoritmos\\Progra I\\src\\progra\\algoritmos\\tec\\metadata\\";
	public void createdFolder(String x){
		File folder=new File(path+x);
			folder.mkdir();
	}
	public void deleteFolder(File x) {
		x.delete();
	}

	
}
