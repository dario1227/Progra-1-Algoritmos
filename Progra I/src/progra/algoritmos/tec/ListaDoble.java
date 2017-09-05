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
	public void add(T json) {
		Nodo<T> nodo=new Nodo<>();
		nodo.valor=json;
		largo++;
		if(head==null){
			this.head=nodo;
			this.tail=this.head;
		}
		else if(head.next==null) {
			this.head.next=nodo;
			this.tail=nodo;
			nodo.prev=this.head;
		}
		else {
			Nodo<T> temp=tail;
			this.tail.next=nodo;
			this.tail=nodo;
			this.tail.prev=temp;
		}
	}
	public void delete(T valor) {
		Nodo<T> nodo=this.head;
		largo--;
		if(head.valor==valor) {
			head=head.next;
		}
		else if(tail.valor==valor) {
			this.tail=this.tail.prev;
			this.tail.next=null;
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
		Nodo<T> nodo =this.head;
		while(nodo!=null) {
			System.out.println(nodo.valor);
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
	public void save() {
		while(this.head!=null) {
			
		}
		
	}
		
	
}