package progra.algoritmos.tec.estructurasDatos;

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
