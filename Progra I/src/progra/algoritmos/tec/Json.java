package progra.algoritmos.tec;

public interface Json {
	void save();
	void read();
	void load();
	void delete();
	void add(String name);
	String getName();
	ListaDoble<Json> getJsons();

}
