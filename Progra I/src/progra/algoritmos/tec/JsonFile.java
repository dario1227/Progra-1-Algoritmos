package progra.algoritmos.tec;
import java.io.File;
import java.io.IOException;

public class JsonFile implements Json{
	String path;
	String name;
	File jsonfile;
	public JsonFile(String name) {
		this.path=null;
		this.name=name+".json";
	}

	@Override
	public void Save() {
		try {
			this.jsonfile.createNewFile();
		}catch (IOException ex) {
			System.out.println(ex);
			
		}
		
	}

	@Override
	public void Read() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete() {
		this.jsonfile.delete();
		
	}
	

}
