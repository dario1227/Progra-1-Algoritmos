package progra.algoritmos.tec;
import progra.algoritmos.tec.estructurasDatos.*;
/**
 * Interface para hacer polimorfismo a los archivos Json y tratarlos de igual manera
 * @author Dario
 *
 */
public interface Json {
	void save();
	void delete();
	void add(String name);
	String getName();
	Lista<Json> getJsons();
	Lista<Lista<String>> getColumnas();
	void addColumna(String nombre,String Tipo, String Predeterminado,String Requerido,String Especial);
	Lista<Lista<String>> getInstancias();
	String getpath();
	void setInstancias(Lista<Lista<String>> listaIns);
	void addInstance(Lista<String> datos);

}
