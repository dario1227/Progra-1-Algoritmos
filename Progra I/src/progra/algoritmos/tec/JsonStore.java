package progra.algoritmos.tec;
import java.io.File;

public class JsonStore implements Json{
	ListaDoble<JsonFile> jsons;
	String path="C:\\Users\\User1\\git\\Progra-1-Algoritmos\\Progra I\\src\\progra\\algoritmos\\tec\\metadata\\";
	File jsonStore;
	public JsonStore(String name) {
		this.path=path+name;
		this.jsons=null;
		File jsonStore=new File(this.path);
	}

	@Override
	public void Save() {
		this.jsonStore.mkdirs();
	}

	@Override
	public void Read() {
		
	}

	@Override
	public void Load() {
		
	}

	@Override
	public void Delete() {
		// TODO Auto-generated method stub
		
	}
	public void Add(JsonFile json) {
		json.path=this.path+"\\"+json.name;
		this.jsons.add(json);
			
		}
		
	}
	

}
