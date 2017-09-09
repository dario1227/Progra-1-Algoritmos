package progra.algoritmos.tec.estructurasDatos;

public class ListaSimple<T> implements Lista<T> {
		Nodo<T> head;
		public ListaSimple() {
			this.head=null;
		}
		@Override
		public void add(T json) {
			Nodo<T> nodo=new Nodo<>();
			nodo.valor=json;
			if(head==null){
				this.head=nodo;
			}
			else {
				Nodo<T> temp=this.head;
				while(temp.next!=null) {
					temp=temp.next;
			}
				temp.next=nodo;
			}
		}
		@Override
		public void delete(T valor) {
			Nodo<T> nodo=this.head;
			if(head.valor==valor) {
				head=head.next;
			}
			else {
				while( nodo.next!=null) {
					Nodo<T>temp=nodo.next;
					if(nodo.next.valor==valor) {
						nodo.next=temp.next;
					}
					nodo=nodo.next;
					
				}
			}
		}
		@Override
		public void print() {
			Nodo<T> nodo =this.head;
			while(nodo!=null) {
				System.out.println(nodo.valor);
				nodo=nodo.next;
			}
		}
		@Override
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
		@Override
		public Nodo<T> getHead() {
			return this.head;
		}
		@Override
		public Nodo<T> getTail() {
			// TODO Auto-generated method stub
			return null;
		}	
		
	}