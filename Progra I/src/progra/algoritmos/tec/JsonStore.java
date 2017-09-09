package progra.algoritmos.tec;
import java.io.File;
import progra.algoritmos.tec.estructurasDatos.*;
public class JsonStore implements Json{
	ListaDobleC<Json> jsons;
	String path="C:\\Users\\User1\\git\\Progra-1-Algoritmos\\Progra I\\src\\progra\\algoritmos\\tec\\metadata\\";
	File jsonStore;
	String name;
	public JsonStore(String name) {
		this.name=name;
		this.path=path+name;
		this.jsons=new ListaDobleC<Json>();
		this.jsonStore=new File(this.path);
	}
	@Override
	public void delete() {
		try {
		Nodo<Json> file=this.jsons.getTail();
		Nodo<Json> end=this.jsons.getTail();
		while(true) {
			file.valor.delete();
			file=file.next;
			if(file==end) {
				break;
			}
		}
		this.jsonStore.delete();
		this.jsons=null;
		System.out.println(this.name+ " ELIMINADO");
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
		Nodo<Json> temp=this.jsons.getTail();
		Nodo<Json> end=this.jsons.getTail();
		while(true) {
			temp.valor.save();
			temp=temp.next;
			if(temp==end) {
				break;
			}
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
	public ListaDobleC<Json> getJsons() {
		return this.jsons;
	}
		
}
	
