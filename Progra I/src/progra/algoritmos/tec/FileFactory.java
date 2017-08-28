package progra.algoritmos.tec;

public class  FileFactory {
	public static Json getFile(String name,FilesTypes tipo ) {
		if(tipo==FilesTypes.Json) {
			Json json=new JsonFile(name);
			return json; 
		}
		else if(tipo==FilesTypes.JsonStore) {
			Json json=new JsonStore(name);
			return json; 
			
		}
		else {
			System.out.println("No Existe ese tipo");
		}
		return null;
		

	}
}
