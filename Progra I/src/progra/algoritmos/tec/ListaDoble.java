package progra.algoritmos.tec;

public class ListaDoble<T> {
	Nodo<T> head;
	Nodo<T> tail;
	private int largo;
	public ListaDoble() {
		this.head=null;
		this.tail=null;
		this.largo=0;
	}
	public void add(T valor) {
		Nodo<T> nodo=new Nodo<>();
		nodo.valor=valor;
		largo++;
		if(head==null){
			head=nodo;
			tail=head;
		}
		else if(head.next==null) {
			head.next=nodo;
			tail=nodo;
			nodo.prev=head;
		}
		else {
			Nodo<T> temp=tail;
			tail.next=nodo;
			tail=nodo;
			tail.prev=temp;
		}
	}
	public void delete(T valor) {
		Nodo<T> nodo=head;
		largo--;
		if(head.valor==valor) {
			head=head.next;
		}
		else if(tail.valor==valor) {
			tail=tail.prev;
			tail.next=null;
		} 
		else {
			while(nodo.valor!=valor) {
				nodo=nodo.next;
			}
			Nodo<T> temp=nodo.next;
			temp.prev=nodo.prev;
			nodo.prev.next=temp;
		}
	}
	public void print() {
		Nodo<T> nodo =head;
		while(nodo!=null) {
			System.out.print(nodo.valor);
			nodo=nodo.next;
		}
	}
	public T search(T valor) {
		Nodo<T> temp=this.head;
		while(temp!=null && temp.valor!=valor) {
			temp=temp.next;
			} 
		if (temp!=null) {
			return valor;
		}
		else {
			System.out.println("No se encontro el valor");
		}
		return null;
	}
}