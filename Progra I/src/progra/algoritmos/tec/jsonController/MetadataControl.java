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

import progra.algoritmos.tec.FileFactory;
import progra.algoritmos.tec.FilesTypes;
import progra.algoritmos.tec.Json;
import progra.algoritmos.tec.ListController;
import progra.algoritmos.tec.estructurasDatos.ListFactory;
import progra.algoritmos.tec.estructurasDatos.ListTypes;
import progra.algoritmos.tec.estructurasDatos.Lista;
import progra.algoritmos.tec.estructurasDatos.Nodo;

public class MetadataControl {
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
}
