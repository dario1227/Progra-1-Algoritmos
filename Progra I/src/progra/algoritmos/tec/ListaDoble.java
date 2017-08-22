package progra.algoritmos.tec;

public class ListaDoble<T> {
	Nodo<T> head;
	Nodo<T> tail;
	public ListaDoble() {
		this.head=null;
		this.tail=null;
	}
	public void add(T valor) {
		Nodo<T> nodo=new Nodo<>();
		nodo.valor=valor;
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
}