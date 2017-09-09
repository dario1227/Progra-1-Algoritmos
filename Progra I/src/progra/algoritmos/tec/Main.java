package progra.algoritmos.tec;
import progra.algoritmos.tec.estructurasDatos.*;
public class Main {

	public static void main(String[] args) {
		try {
			Lista<Json> linkedDB=ListFactory.getlist(ListTypes.Circular);
			linkedDB.add(FileFactory.getFile("gola", FilesTypes.JsonStore));
			linkedDB.add(FileFactory.getFile("fola", FilesTypes.JsonStore));
			linkedDB.add(FileFactory.getFile("tola", FilesTypes.JsonStore));
			linkedDB.add(FileFactory.getFile("hola", FilesTypes.JsonStore));
			linkedDB.print();
			ListController.search(linkedDB,"fola").add("jsoncreado1");
			ListController.search(linkedDB,"fola").add("jsoncreado2");
			ListController.search(linkedDB,"gola").add("jsoncreado3");
			ListController.search(linkedDB,"tola").add("jsoncreado4");
			ListController.search(linkedDB,"hola").add("jsoncreado5");
			ListController.search(linkedDB, "jsoncreado3");
			ListController.delete(linkedDB, "fola");
			ListController.commit(linkedDB);

		}
		catch (Exception ex) {
			System.out.println(ex);
		}
	
	}

}

