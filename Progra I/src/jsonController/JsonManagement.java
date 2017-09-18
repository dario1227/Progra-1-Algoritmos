package jsonController;
import progra.algoritmos.tec.estructurasDatos.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONTokener;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import progra.algoritmos.tec.*;
public class JsonManagement {
	@SuppressWarnings("unchecked")
	public static JSONArray convertMetadata(Lista<Json> metadata) {
		Nodo <Json> temp=metadata.getHead();
		JSONArray metadataArray=new JSONArray();
		while(temp!=null) {
			JSONObject store=new JSONObject();
			JSONArray jsons=new JSONArray();
			store.put("name", temp.getValor().getName());
			Lista<Json> Jsons=temp.getValor().getJsons();
			Nodo<Json> temp2=Jsons.getTail();
			Nodo<Json> end=Jsons.getTail();
			while(temp2!=null) {
				JSONObject json=new JSONObject();
				json.put("Nombre",temp2.getValor().getName());
				JSONArray columnas=new JSONArray();
				Nodo<Lista<String>> temp3=temp2.getValor().getColumnas().getHead();
				while(temp3!=null) {
					Nodo<String> Columna=temp3.getValor().getHead();
					String Nombre=Columna.getValor();
					String Tipo=Columna.next.getValor();
					String Especial=Columna.next.next.getValor();
					String requerido=Columna.next.next.next.getValor();
					String Predeterminado=Columna.next.next.next.next.getValor();
					JSONObject columna=new JSONObject();
					columna.put("Nombre", Nombre);
					columna.put("Tipo", Tipo);
					columna.put("Especial", Especial);
					columna.put("Requerido", requerido);
					columna.put("Predeterminado", Predeterminado);
					columnas.add(columna);
					temp3=temp3.next;
				}
				json.put("Columnas", columnas);
				jsons.add(json);
				temp2=temp2.next;
				if(temp2==end) {
					store.put("Jsons",jsons);
					break;
				}
			}
			metadataArray.add(store);
			temp=temp.next;
		}
		return metadataArray;
		
	}
	public static void saveMetadata(JSONArray metadata) throws IOException, ParseException {
		File metadataJ=new File("C:\\Users\\User1\\git\\Progra-1-Algoritmos\\Progra I\\src\\progra\\algoritmos\\tec\\metadata\\METADATA.json");
		try(FileWriter writer=new FileWriter(metadataJ)){
		JSONTokener tokener=new JSONTokener(metadata.toJSONString());
		org.json.JSONArray converted=new org.json.JSONArray(tokener);
		writer.write(converted.toString(4));
		writer.flush();
		}
		catch (Exception e) {
		
		}
		
	}
	@SuppressWarnings("unchecked")
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
			while(Valor!=null) {
				instancia.put(Valor.getValor(),insta.getValor().getHead().getValor());
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
	public static Lista<Json> readMetadata() throws IOException, ParseException{
		try {
		Lista<Json> linkedDB=ListFactory.getlist(ListTypes.Doble);
		JSONParser parser=new JSONParser();
		FileReader reader=new FileReader("C:\\Users\\User1\\git\\Progra-1-Algoritmos\\Progra I\\src\\progra\\algoritmos\\tec\\metadata\\METADATA.json");
		JSONArray jason =(JSONArray) parser.parse(reader); 
		int largo=jason.size();
		int index=0;
		while(index<largo) {
			JSONObject store=(JSONObject) jason.get(index);
			String nombre=(String) store.get("name");
			linkedDB.add(FileFactory.getFile(nombre,FilesTypes.JsonStore));
			JSONArray jsons=(JSONArray)store.get("Jsons");
			int largoJ=jsons.size();
			int index2=0;
			while(index2<largoJ) {
				JSONObject Json=(JSONObject) jsons.get(index2);
				String NameJ=(String)Json.get("Nombre");
				String NameJ2=NameJ.substring(0, NameJ.length()-5);
				ListController.search(linkedDB, nombre).add(NameJ2);
				JSONArray columnas=(JSONArray)Json.get("Columnas");
				int largoC=columnas.size();
				int index3=0;
				while(index3<largoC) {
					JSONObject columna=(JSONObject) columnas.get(index3);
					String Nombre=(String)columna.get("Nombre");
					String Tipo=(String)columna.get("Tipo");
					String Predeterminado=(String)columna.get("Predeterminado");
					String Especial=(String)columna.get("Especial");
					String Requerido=(String)columna.get("Requerido");
					ListController.search(linkedDB,NameJ2).addColumna(Nombre, Tipo, Predeterminado, Requerido, Especial);
					index3++;
				}
				index2++;
			}
			index++;
		}
		return linkedDB;
		}
		catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	public static Lista<Lista<String>> getAllStores() throws IOException, ParseException {
		Lista<Lista<String>> Stores=ListFactory.getlist(ListTypes.Doble);
		JSONParser parser=new JSONParser();
		FileReader reader=new FileReader("C:\\Users\\User1\\git\\Progra-1-Algoritmos\\Progra I\\src\\progra\\algoritmos\\tec\\metadata\\METADATA.json");
		JSONArray jason =(JSONArray) parser.parse(reader); 
		int largo=jason.size();
		int index=0;
		while(index<largo) {
			Lista<String> storeL=ListFactory.getlist(ListTypes.Doble);
			JSONObject store=(JSONObject) jason.get(index);
			JSONArray jsons=(JSONArray)store.get("Jsons");
			String nombre=(String) store.get("name");
			storeL.add(nombre);
			int largoJ=jsons.size();
			int index2=0;
			while(index2<largoJ) {
				JSONObject Json=(JSONObject) jsons.get(index2);
				String NameJ=(String)Json.get("Nombre");
				storeL.add(NameJ);
				index2++;
			}
			Stores.add(storeL);
			index++;
		}
		return Stores;
	}
	public static void readInstances(Lista<Json> metadata) throws IOException, ParseException {
		try {
		JSONParser parser=new JSONParser();
		Lista<Lista<String>> jsons=JsonManagement.getAllStores();
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
