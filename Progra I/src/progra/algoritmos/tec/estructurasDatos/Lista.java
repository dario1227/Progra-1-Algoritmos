package progra.algoritmos.tec.estructurasDatos;
/**
 * Polimorfismo para listas
 * @author Dario
 *
 * @param <T> tipo de valores que tendra la lista
 */
public interface Lista<T> {
	void add(T valor);
	void delete(T valor);
	void print();
	T search(T valor);
	Nodo<T> getHead();
	Nodo<T> getTail();
	void setLista(Lista<T>lista);

}
