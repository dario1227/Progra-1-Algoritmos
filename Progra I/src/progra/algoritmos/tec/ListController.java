package progra.algoritmos.tec;
import java.io.IOException;

import org.json.simple.parser.ParseException;

import progra.algoritmos.tec.estructurasDatos.*;
import progra.algoritmos.tec.jsonController.Instances;
import progra.algoritmos.tec.jsonController.MetadataControl;
import progra.algoritmos.tec.jsonController.Stores;
public class ListController {
	static Lista<Json> deletes=ListFactory.getlist(ListTypes.Simple);
	public static Json search(Lista<Json>lista,String name) {
		if(lista.getHead()==null) {
			System.out.println("LISTA VACIA");
		}
		else {
		Nodo<Json> temp=lista.getHead();
		try {
		while(temp!=null) {
			if(temp.getValor().getName().equalsIgnoreCase(name)) {
				System.out.println(temp.getValor().getName()+" ENCONTRADO");
				return temp.getValor();
			}
			else {
				Lista<Json> store=temp.getValor().getJsons();
				Nodo<Json> temp2=store.getTail();
				Nodo<Json> end=store.getTail();
				while(temp2!=null) {
					if(temp2.getValor().getName().equalsIgnoreCase(name+".json")) {
						System.out.println(temp2.getValor().getName()+" ENCONTRADO");
						return temp2.getValor();
					}
					else {
						temp2=temp2.next;
						if (temp2==end) {
							break;
						}
					}
				}
				temp=temp.next;
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

	public static void commit(Lista<Json> lista) throws IOException, ParseException {
		Nodo<Json> temp=lista.getHead();
		Nodo<Json> delete=ListController.deletes.getHead();
		System.out.println("COMMIT!!!!!");
		while(temp!=null) {
			System.out.println(temp.getValor().getName());
			temp.getValor().save();
			System.out.println(temp.getValor().getName()+" GUARDADO");
			temp=temp.next;
		}
		while(delete!=null) {
			try {
				while(delete!=null) {
				delete.getValor().delete();
				delete=delete.next;
				}
			}
			catch (NullPointerException ex) {
				}
			}
		MetadataControl.saveMetadata(MetadataControl.convertMetadata(lista));
		Lista<Lista<String>>listaJsons=Stores.getAllStores();
		Nodo<Lista<String>> json=listaJsons.getHead();
		while(json!=null) {
			Nodo<String>nombre =json.getValor().getHead().next;
			while(nombre!=null) {
				Instances.saveInstances(ListController.search(lista, nombre.getValor().substring(0, nombre.getValor().length()-5)));
				nombre=nombre.next;
			}
		json=json.next;
		}
	}
	public static void  delete(Lista<Json> list,String name) {
		Json toDelete=ListController.search(list, name);
		list.delete(ListController.search(list, name));
		deletes.add(toDelete);
	
	}
	public static void deleteJ(Lista<Json> list,String name,String Store) {
		Json toDelete=ListController.search(list, name);
		ListController.search(list, Store).getJsons().delete(ListController.search(list, name));
		deletes.add(toDelete);
	}
}
