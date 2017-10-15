package progra.algoritmos.tec;
import java.io.File;

import progra.algoritmos.tec.estructurasDatos.*;
/**
 * Clase qu crea los Stores
 * @author Dario
 *
 */
public class JsonStore implements Json{
	Lista<Json> jsons;
	String path="C:\\Users\\User1\\git\\Progra-1-Algoritmos\\Progra I\\src\\progra\\algoritmos\\tec\\metadata\\";
	File jsonStore;
	String name;
	/**
	 * Crea una instancia con el nombre que se le ponga en el parametro name
	 * @param name
	 */
	public JsonStore(String name) {
		this.name=name;
		this.path=path+name;
		this.jsons=ListFactory.getlist(ListTypes.Circular);
		this.jsonStore=new File(this.path);
	}
	@Override
	/**
	 * Elimina todos los jsons y luego el store
	 */
	public void delete() {
		try {
		Nodo<Json> file=this.jsons.getTail();
		Nodo<Json> end=this.jsons.getTail();
		while(true) {
			if(file==null) {
				break;
			}
			else {
				file.getValor().delete();
				file=file.next;
				if(file==end) {
					break;
				}
				
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
	/**
	 * Anañade un JsonFile a la lista de jsons
	 */
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
			if(temp==null) {
				break;
			}
			else {
				temp.getValor().save();
				temp=temp.next;
				if(temp==end) {
					break;
				}
			}
		}
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Lista<Json> getJsons() {
		return this.jsons;
	}
	@Override
	public Lista<Lista<String>> getColumnas() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void addColumna(String nombre, String Tipo, String Predeterminado, String Requerido, String Especial) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Lista<Lista<String>> getInstancias() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getpath() {
		return this.path;
	}
	@Override
	public void setInstancias(Lista<Lista<String>> listaIns) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addInstance(Lista<String> datos) {
		// TODO Auto-generated method stub
		
	}
		
}
	
