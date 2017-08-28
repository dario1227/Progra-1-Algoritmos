package progra.algoritmos.tec;
public class Main {

	public static void main(String[] args) {
		ListaDoble<Integer> linkedDB=new ListaDoble<>();
		linkedDB.add(2);
		linkedDB.add(4);
		linkedDB.add(6);
		linkedDB.add(8);
		linkedDB.print();
		System.out.println(linkedDB.search(8));
		Json archivo=FileFactory.getFile("Hola", FilesTypes.JsonStore);
		archivo.Delete();
		
	}

}

