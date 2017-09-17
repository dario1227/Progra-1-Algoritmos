package progra.algoritmos.tec;
import progra.algoritmos.tec.estructurasDatos.*;
public interface Json {
	void save();
	void delete();
	void add(String name);
	String getName();
	Lista<Json> getJsons();
	Lista<Lista<String>> getColumnas();
	void addColumna(String nombre,String Tipo, String Predeterminado,String Requerido,String Especial);
	void addInstance();
	Lista<Lista<String>> getInstancias();
	String getpath();

}
