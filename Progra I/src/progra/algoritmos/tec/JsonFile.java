package progra.algoritmos.tec;
import progra.algoritmos.tec.estructurasDatos.*;

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
	public void save() {
		try {
			this.jsonfile.createNewFile();
		}catch (IOException ex) {
			System.out.println("ERROR");
			
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
		// TODO Auto-generated method stub
		return null;
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

	

}
