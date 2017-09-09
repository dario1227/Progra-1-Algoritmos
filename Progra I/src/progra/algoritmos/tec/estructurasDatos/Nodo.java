package progra.algoritmos.tec.estructurasDatos;

public class Nodo<T> {
	public Nodo<T> next;
	Nodo<T> prev;
	public T valor;
	public Nodo() {
		this.next=null;
		this.prev=null;
		this.valor=null;
	}
	public T getValor() {
		return valor;
	}
	public void setValor(T valor) {
		this.valor=valor;
	}
}
