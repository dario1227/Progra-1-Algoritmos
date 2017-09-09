package progra.algoritmos.tec;
import progra.algoritmos.tec.estructurasDatos.*;
public class ListController {
	static Lista<Json> deletes=ListFactory.getlist(ListTypes.Simple);
	public static Json search(Lista<Json>lista,String name) {
		if(lista.getTail()==null) {
			System.out.println("LISTA VACIA");
		}
		else {
		Nodo<Json> temp=lista.getTail();
		Nodo<Json> end=lista.getTail();
		try {
		while(temp!=null) {
			if(temp.valor.getName().equalsIgnoreCase(name)) {
				System.out.println(temp.valor.getName()+" ENCONTRADO");
				return temp.valor;
			}
			else {
				ListaDobleC<Json> store=temp.valor.getJsons();
				Nodo<Json> temp2=store.getTail();
				Nodo<Json> end2=store.getTail();
				while(temp2!=null) {
					if(temp2.valor.getName().equalsIgnoreCase(name+".json")) {
						System.out.println(temp2.valor.getName()+" ENCONTRADO");
						return temp2.valor;
					}
					else {
						temp2=temp2.next;
						if (temp2==end2) {
							break;
						}
					}
				}
				temp=temp.next;
				if(temp==end) {
					break;
				}
			}
		}
	}
		catch (NullPointerException ex) {
			System.out.println("ERROR");
			}
		}
	
		System.out.println("NO SE ENCONTRO EL ARCHIVO");
		return null;
	}

	public static void commit(Lista<Json> lista) {
		Nodo<Json> temp=lista.getTail();
		Nodo<Json> end=lista.getTail();
		Nodo<Json> delete=ListController.deletes.getHead();
		while(true) {
			temp.valor.save();
			temp=temp.next;
			if(temp==end) {
				break;
			}
		}
		while(true) {
			if(delete==null){
				break;
			}
			else {
				while(delete!=null) {
					
				delete.valor.delete();
				delete=delete.next;
				}
			}
		}
	}
	public static void  delete(Lista<Json> list,String name) {
		Json toDelete=ListController.search(list, name);
		deletes.add(toDelete);
	}
}
