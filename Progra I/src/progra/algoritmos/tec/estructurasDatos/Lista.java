package progra.algoritmos.tec.estructurasDatos;

public interface Lista<T> {
	void add(T valor);
	void delete(T valor);
	void print();
	T search(T valor);
	Nodo<T> getHead();
	Nodo<T> getTail();

}
