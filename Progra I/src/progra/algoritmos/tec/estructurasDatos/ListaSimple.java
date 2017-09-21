package progra.algoritmos.tec.estructurasDatos;
/**
 * Estructura de lista Simple
 * @author Dario
 *
 * @param <T>Tipo de datos que almacenara
 */
public class ListaSimple<T> implements Lista<T> {
		Nodo<T> head;
		public ListaSimple() {
			this.head=null;
		}
		@Override
		public void add(T json) {
			Nodo<T> nodo=new Nodo<>();
			nodo.setValor(json);
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
			if(head.getValor()==valor) {
				head=head.next;
			}
			else {
				while( nodo.next!=null) {
					Nodo<T>temp=nodo.next;
					if(nodo.next.getValor()==valor) {
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
			return this.head;
		}
		@Override
		public Nodo<T> getTail() {
			// TODO Auto-generated method stub
			return null;
		}	
		
	}