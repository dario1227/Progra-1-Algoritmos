package progra.algoritmos.tec;
import java.io.File;

public class JsonFile implements Json{
	String path;
	String name;
	File jsonfile;
	public JsonFile(String name) {
		this.path=null;
		this.name=name+".json";
		//tomar en cuenta el file()
	}

	@Override
	public void Save() {
		
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
		// TODO Auto-generated method stub
		
	}
	

}
