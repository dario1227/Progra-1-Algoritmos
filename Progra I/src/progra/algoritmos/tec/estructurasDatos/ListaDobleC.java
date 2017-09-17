package progra.algoritmos.tec.estructurasDatos;

public class ListaDobleC<T> implements Lista<T>{
	Nodo<T> tail;
	public ListaDobleC() {
		this.tail=null;
	}
	@Override
	public void add(T json) {
		Nodo<T> nodo=new Nodo<>();
		nodo.setValor(json);
		if(this.tail==null){
			this.tail=nodo;
			this.tail.next=this.tail;
			this.tail.prev=this.tail;
		}
		else if(this.tail.next==this.tail) {
			this.tail.next=nodo;
			nodo.prev=this.tail;
			nodo.next=this.tail;
			this.tail.prev=nodo;
			this.tail=nodo;
		}
		else {
			Nodo<T> temp=tail.next;
			this.tail.next=nodo;
			nodo.prev=this.tail;
			this.tail=nodo;
			this.tail.next=temp;
			temp.prev=this.tail;
		}
	}
	@Override
	public void delete(T valor) {
		if (this.tail==null) {
			System.out.println("LISTA VACIA");
		}
		else {
			Nodo<T> nodo=this.tail;
			Nodo<T> end=this.tail;
			if(this.tail.getValor()==valor) {
				Nodo<T> temp=this.tail.next;
				if(temp==this.tail) {
					this.tail=null;
				}
				else if(temp.next==this.tail) {
					this.tail=temp;
					this.tail.next=this.tail;
					this.tail.prev=this.tail;
				}
				else {
					this.tail.prev.next=temp;
					temp.prev=this.tail.prev;
					this.tail=this.tail.prev;
					
					
				}
			}
			else {
				nodo=nodo.next;
				while(nodo.getValor()!=valor && nodo!=end) {
					nodo=nodo.next;
				}
				if(nodo==end) {
					System.out.println("NO SE ENCONTRO");
				}
				else {
					Nodo<T> temp=nodo.next;
					temp.prev=nodo.prev;
					nodo.prev.next=temp;	
				}
			}
		}
	}
	@Override
	public void print() {
		Nodo<T> nodo =this.tail;
		Nodo<T> end=this.tail;
		while(true) {
			System.out.println(nodo.next.getValor());
			nodo=nodo.next;
			if(nodo==end) {
				break;
			}
		}
	}
	@Override
	public T search(T valor) {
		Nodo<T> nodo=this.tail;
		Nodo<T> end=this.tail;
		while(nodo!=null && nodo.getValor()!=valor) {
			nodo=nodo.next;
			if(nodo==end) {
				System.out.println("No se encontro el valor");
				break;
			}
		} 
		if (nodo.getValor()==valor) {
			System.out.println("ENCONTRADO");
			return valor;
		}
		return null;
	}
	@Override
	public Nodo<T> getHead() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Nodo<T> getTail() {
		return this.tail;
	}
		
	
}