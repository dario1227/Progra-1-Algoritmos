package progra.algoritmos.tec;

public class ListController {
	static ListaDoble<Json> deletes=new ListaDoble<>();
	public static Json search(ListaDoble<Json>lista,String name) {
		if(lista.tail==null) {
			System.out.println("LISTA VACIA");
		}
		else {
		Nodo<Json> temp=lista.tail;
		Nodo<Json> end=lista.tail;
		try {
		while(temp!=null) {
			if(temp.valor.getName().equalsIgnoreCase(name)) {
				System.out.println(temp.valor.getName()+" ENCONTRADO");
				return temp.valor;
			}
			else {
				ListaDoble<Json> store=temp.valor.getJsons();
				Nodo<Json> temp2=store.tail;
				Nodo<Json> end2=store.tail;
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

	public static void commit(ListaDoble<Json> list) {
		Nodo<Json> temp=list.tail;
		Nodo<Json> end=list.tail;
		Nodo<Json> delete=ListController.deletes.tail;
		Nodo<Json> endDelete=ListController.deletes.tail;
		while(true) {
			temp.valor.save();
			temp=temp.next;
			if(temp==end) {
				break;
			}
		}
		while(true) {
			delete.valor.delete();
			delete=delete.next;
			if(delete==endDelete) {
				break;
			}
		}
	}
	public static void  delete(ListaDoble<Json> list,String name) {
		Json toDelete=ListController.search(list, name);
		deletes.add(toDelete);
	}
}
