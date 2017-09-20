package progra.algoritmos.tec.jsonController;

import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import progra.algoritmos.tec.estructurasDatos.ListFactory;
import progra.algoritmos.tec.estructurasDatos.ListTypes;
import progra.algoritmos.tec.estructurasDatos.Lista;

public class Stores {
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
			int largoJ;
			if(jsons==null) {
				largoJ=0;
			}else {
				largoJ=jsons.size();
			}
			
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
}
