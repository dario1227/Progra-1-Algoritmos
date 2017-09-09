package progra.algoritmos.tec;
import progra.algoritmos.tec.estructurasDatos.*;
public class ListFactory {
	public static <T> Lista<T> getlist(ListTypes tipo) {
		if(tipo==ListTypes.Circular) {
			ListaDobleC<T> list=new ListaDobleC<>();
			return list; 
		}
		else if(tipo==ListTypes.Simple) {
			ListaSimple<T> list=new ListaSimple<>();
			return list; 
			
		}
		else if(tipo==ListTypes.Doble) {
			ListaDoble<T> list=new ListaDoble<>();
			return list; 
			
		}
		return null;
		

	}

}
