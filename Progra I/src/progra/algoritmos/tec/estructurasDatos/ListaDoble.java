package progra.algoritmos.tec.estructurasDatos;
/**
 * Estructura de listaDoblemente enlazada
 * @author Dario
 *
 * @param <T> tipo de valor que tendra la lista
 */
public class ListaDoble<T> implements Lista<T>{
		Nodo<T> head;
		Nodo<T> tail;
		public ListaDoble() {
			this.head=null;
			this.tail=null;
		}
		@Override
		public void add(T json) {
			Nodo<T> nodo=new Nodo<>();
			nodo.setValor(json);
			if(this.head==null){
				this.head=nodo;
				this.tail=this.head;
			}
			else if(this.head.next==null) {
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
		@Override
		public void delete(T valor) {
			Nodo<T> nodo=this.head;
			if(this.head.getValor()==valor) {
				this.head=head.next;
			}
			else if(tail.getValor()==valor) {
				this.tail=tail.prev;
				this.tail.next=null;
			} 
			else {
				while(nodo.getValor()!=valor) {
					nodo=nodo.next;
				}
				Nodo<T> temp=nodo.next;
				temp.prev=nodo.prev;
				nodo.prev.next=temp;
			}
		}
		@Override
		public void print() {
			Nodo<T> nodo =this.head;
			while(nodo!=null) {
				System.out.println(nodo.getValor());
				nodo=nodo.next;
			}
		}
		@Override
		public T search(T valor) {
			Nodo<T> temp=this.head;
			while(temp!=null && temp.getValor()!=valor) {
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
		@Override
		public Nodo<T> getHead() {
			// TODO Auto-generated method stub
			return this.head;
		}
		@Override
		public Nodo<T> getTail() {
			// TODO Auto-generated method stub
			return this.tail;
		}
		@Override
		public void setLista(Lista<T> lista) {
			this.head=lista.getHead();
			this.tail=lista.getTail();
		}
	}
