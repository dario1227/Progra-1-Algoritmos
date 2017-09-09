package progra.algoritmos.tec;
import progra.algoritmos.tec.estructurasDatos.*;
public interface Json {
	void save();
	void read();
	void load();
	void delete();
	void add(String name);
	String getName();
	ListaDobleC<Json> getJsons();

}
