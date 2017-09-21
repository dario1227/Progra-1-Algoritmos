package progra.algoritmos.tec.estructurasDatos;
/**
 * Hace una lista segun el tipo que se le indique
 * @author Dario
 *
 */
public class ListFactory {
	/**
	 * 
	 * @return una lista segun el tipo seleccionado
	 */
	public static <T> Lista<T> getlist(ListTypes type) {
		if(type==ListTypes.Circular) {
			ListaDobleC<T> list=new ListaDobleC<>();
			return list; 
		}
		else if(type==ListTypes.Simple) {
			ListaSimple<T> list=new ListaSimple<>();
			return list; 
			
		}
		else if(type==ListTypes.Doble) {
			ListaDoble<T> list=new ListaDoble<>();
			return list; 
			
		}
		return null;
		

	}

}
