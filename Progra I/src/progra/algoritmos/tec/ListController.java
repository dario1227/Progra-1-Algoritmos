package progra.algoritmos.tec;

public class ListController {
	static Json result=null;
	public static Json search(ListaDoble<Json>lista,String name) {
		if(lista.head==null) {
			System.out.println("LISTA VACIA");
		}
		else {
		Nodo<Json> temp=lista.head;
		try {
		while(temp!=null) {
			if(temp.valor.getName().equalsIgnoreCase(name)) {
				System.out.println(temp.valor.getName());
				result=temp.valor;
				return temp.valor;
			}
			else {
				temp=temp.next;
			}
		}
	}
		catch (NullPointerException ex) {
			System.out.println("ERROR");
			}
		}
	
		System.out.println("NO SE ENCONTRO EL ARCHIVO");
		Nodo<Json> store=lista.head;
		try {
		while(store!=null) {
			ListController.search(store.valor.getJsons(), name);
			store=store.next;
			}
		}
		catch(NullPointerException ex){
		}
		return result;
	}

	public static void commit(ListaDoble<Json> list) {
		Nodo<Json> temp=list.head;
		while(temp!=null) {
			temp.valor.save();
			temp=temp.next;
		}
	}
	public static void delete(ListaDoble<Json> list,String name) {
		try {
		ListController.search(list, name).delete();
		}
		catch (NullPointerException ex) {
			System.out.println("NO EXISTE");
		}
	}
}
