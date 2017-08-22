package progra.algoritmos.tec;

public class Nodo<T> {
	Nodo<T> next;
	Nodo<T> prev;
	T valor;
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
