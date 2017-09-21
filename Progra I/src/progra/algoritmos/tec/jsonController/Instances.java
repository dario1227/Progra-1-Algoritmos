package progra.algoritmos.tec.jsonController;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONTokener;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import progra.algoritmos.tec.Json;
import progra.algoritmos.tec.ListController;
import progra.algoritmos.tec.estructurasDatos.ListFactory;
import progra.algoritmos.tec.estructurasDatos.ListTypes;
import progra.algoritmos.tec.estructurasDatos.Lista;
import progra.algoritmos.tec.estructurasDatos.Nodo;
/**
 * Manejo de las Instancias
 * @author Dario
 *
 */
public class Instances {
	@SuppressWarnings("unchecked")
	/*
	 * Guarda en memoria las instancias
	 */
	public static void saveInstances(Json jason) {
		JSONObject Json=new JSONObject();
		Json.put("Name",jason.getName());
		JSONArray instancias=new JSONArray();
		Lista<String> nombreColumnas=ListFactory.getlist(ListTypes.Simple);
		Nodo<Lista<String>> temp=jason.getColumnas().getHead();
		while(temp!=null) {
			String nombre =temp.getValor().getHead().getValor();
			nombreColumnas.add(nombre);
			temp=temp.next;
		}
		Nodo<String> Valor=nombreColumnas.getHead();
		Nodo<Lista<String>> insta=jason.getInstancias().getHead();
		while(insta!=null) {
			JSONObject instancia=new JSONObject();
			Nodo<String>dato=insta.getValor().getHead();
			while(Valor!=null) {
				instancia.put(Valor.getValor(),dato.getValor());
				System.out.println(dato.getValor());
				dato=dato.next;
				Valor=Valor.next;
			}
			instancias.add(instancia);
			insta=insta.next;
			Valor=nombreColumnas.getHead();
		}
		Json.put("Instancias",instancias);
		File ArchivoJ=new File(jason.getpath());
		try(FileWriter writer=new FileWriter(ArchivoJ)){
			JSONTokener tokener=new JSONTokener(Json.toJSONString());
			org.json.JSONObject converted=new org.json.JSONObject(tokener);
			writer.write(converted.toString(4));
			writer.flush();
		}
		catch (Exception e) {
			System.out.println(e);
		
		}
	}
	/**
	 * Carga las instancias en memoria
	 * @param metadata
	 * @throws IOException en caso de no existir el archivo
	 * @throws ParseException en caso de que el archivo este vacio
	 */
	public static void readInstances(Lista<Json> metadata) throws IOException, ParseException {
		try {
		JSONParser parser=new JSONParser();
		Lista<Lista<String>> jsons=Stores.getAllStores();
		Nodo<Lista<String>> store=jsons.getHead();
		while(store!=null) {
			Nodo<String> nombre=store.getValor().getHead();
			Nodo<String>temp=nombre.next;
			while(temp!=null) {
				
				Lista<Lista<String>> instanciasLeidas=ListFactory.getlist(ListTypes.Simple);
				String JsonName=temp.getValor();
				String JsonNameR=JsonName.substring(0,JsonName.length()-5);
				Lista<Lista<String>> columnas=ListController.search(metadata,JsonNameR).getColumnas();
				Lista<String> nombres=ListFactory.getlist(ListTypes.Simple);
				Nodo<Lista<String>> primero=columnas.getHead();
				while(primero!=null) {
					nombres.add(primero.getValor().getHead().getValor());
					primero=primero.next;
					}
				FileReader reader=new FileReader("C:\\Users\\User1\\git\\Progra-1-Algoritmos\\Progra I\\src\\progra\\algoritmos\\tec\\metadata\\"+nombre.getValor()+"\\"+JsonName);
				File file=new File("C:\\Users\\User1\\git\\Progra-1-Algoritmos\\Progra I\\src\\progra\\algoritmos\\tec\\metadata\\"+nombre.getValor()+"\\"+JsonName);
				if(file.length()!=0) {
				JSONObject json=(JSONObject)parser.parse(reader);
				JSONArray Instancias=(JSONArray) json.get("Instancias");
				int largo=Instancias.size();
				int index=0;
				while(index<largo) {
					JSONObject instanciaR=(JSONObject) Instancias.get(index);
					Lista<String> instancia=ListFactory.getlist(ListTypes.Simple);
					Nodo<String> inicio=nombres.getHead();
					while(inicio!=null) {
						instancia.add((String) instanciaR.get(inicio.getValor()));
						inicio=inicio.next;
					}
					instanciasLeidas.add(instancia);
					index++;
					}
				ListController.search(metadata,JsonNameR).setInstancias(instanciasLeidas);
				}
				temp=temp.next;
				}

			store=store.next;
		}
		}
		catch (Exception e) {
			System.out.println(e);
		}

	}

}
