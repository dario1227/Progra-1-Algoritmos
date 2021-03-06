package progra.algoritmos.tec;
import progra.algoritmos.tec.estructurasDatos.*;

import java.io.File;
import java.io.IOException;
/**
 * Jsons que se crean en cada Store, con sus resoectivas instancias y valores de las columnas
 * @author Dario
 *
 */
public class JsonFile implements Json{
	String path;
	String name;
	File jsonfile;
	Lista<Lista<String>> listaColumnas;
	Lista<Lista<String>> listaInstancias;
	/**
	 * Se crea una instancia segun el valor que se ponga en el parametro
	 * @param name
	 */
	public JsonFile(String name) {
		this.path=null;
		this.name=name+".json";
		this.listaColumnas=ListFactory.getlist(ListTypes.Simple);
		this.listaInstancias=ListFactory.getlist(ListTypes.Simple);
	}
	@Override
	public void setInstancias(Lista<Lista<String>> listaIns) {
		this.listaInstancias=listaIns;
		
	}
	@Override
	/**
	 * me a�ade una instancia nueva a la lista de instancias
	 */
	public void addInstance(Lista<String>datos) {
		Lista<String> instancia=ListFactory.getlist(ListTypes.Simple);
		Nodo<Lista<String>> temp=this.listaColumnas.getHead();
		Nodo<String> dato=datos.getHead();
		while(temp!=null) {
			System.out.println(dato.getValor());
			instancia.add(dato.getValor());
			dato=dato.next;
			temp=temp.next;
		}
		this.listaInstancias.add(instancia);
	}
	/**
	 * A�ade una columna nueva, segun el Store Creado
	 */
	@Override
	public void addColumna(String nombre,String Tipo, String Predeterminado,String Requerido,String Especial) {
		Lista<String> valores=ListFactory.getlist(ListTypes.Simple);
		valores.add(nombre);
		valores.add(Tipo);
		valores.add(Especial);
		valores.add(Requerido);
		valores.add(Predeterminado);
		this.listaColumnas.add(valores);
	}
	@Override
	public void save() {
		try {
			this.jsonfile.createNewFile();
		}catch (IOException ex) {
			System.out.println("ERROR");
			
		}
		
	}
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void delete() {
		try {
		this.jsonfile.delete();
		System.out.println(this.name+" ELIMINADO");
	}
		catch (NullPointerException ex) {
		System.out.println("NO EXISTE");
		}
	}
	@Override
	public void add(String name) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Lista<Json> getJsons() {
		return null;
	}
	@Override
	public Lista<Lista<String>> getColumnas() {
		// TODO Auto-generated method stub
		return this.listaColumnas;
	}
	@Override 
	public Lista<Lista<String>> getInstancias(){
		return this.listaInstancias;
	}
	@Override
	public String getpath() {
		// TODO Auto-generated method stub
		return this.path;
	}
	
	

}
