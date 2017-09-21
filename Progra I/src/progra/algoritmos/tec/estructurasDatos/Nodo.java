package progra.algoritmos.tec.estructurasDatos;
/**
 * Corresponde a las posiciones de los valores en las listas
 * @author Dario
 *
 * @param <T> tipo de valor que tendra el nodo
 */
public class Nodo<T> {
	public Nodo<T> next;
	public Nodo<T> prev;
	private T valor;
	public Nodo() {
		this.next=null;
		this.prev=null;
		this.valor=null;
	}
	public T getValor() {
		return this.valor;
	}
	public void setValor(T Valor) {
		this.valor=Valor;
	}
}
