package progra.algoritmos.tec;
import java.io.File;

public class JsonStore implements Json{
	ListaDoble<Json> jsons;
	String path="C:\\Users\\User1\\git\\Progra-1-Algoritmos\\Progra I\\src\\progra\\algoritmos\\tec\\metadata\\";
	File jsonStore;
	String name;
	public JsonStore(String name) {
		this.name=name;
		this.path=path+name;
		this.jsons=new ListaDoble<Json>();
		this.jsonStore=new File(this.path);
	}
	@Override
	public void delete() {
		try {
		Nodo<Json> file=this.jsons.head;
		while(file!=null) {
			file.valor.delete();
			file=file.next;
		}
		this.jsonStore.delete();
		this.jsons=null;
		System.out.println("STORE ELIMINADO");
		System.out.println(this.path);
		}catch(NullPointerException ex) {
			System.out.println("NO EXISTE");
		}
	}
	@Override
	public void add(String name) {
		JsonFile json=new JsonFile(name);
		json.path=this.path+"\\"+json.name;
		json.jsonfile=new File(json.path);
		this.jsons.add(json);
	}

	@Override
	public void save() {
		this.jsonStore.mkdirs();
		Nodo<Json> temp=this.jsons.head;
		while(temp!=null) {
			temp.valor.save();
			temp=temp.next;
		}
		
	}

	@Override
	public void read() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public ListaDoble<Json> getJsons() {
		return this.jsons;
	}
		
}
	
