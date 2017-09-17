package jsonController;
import progra.algoritmos.tec.estructurasDatos.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONTokener;
import org.json.simple.*;
import org.json.simple.parser.ParseException;

import progra.algoritmos.tec.*;
public class JsonManagement {
	@SuppressWarnings("unchecked")
	public static JSONArray convertMetadata(Lista<Json> metadata) {
		Nodo <Json> temp=metadata.getHead();
		JSONArray metadataArray=new JSONArray();
		while(temp!=null) {
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
				metadataArray.add(json);
				temp2=temp2.next;
				if(temp2==end) {
					break;
				}
			}
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
		System.out.println(instancias);
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

}
