package progra.algoritmos.tec.GUI;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import progra.algoritmos.tec.FileFactory;
import progra.algoritmos.tec.FilesTypes;
import progra.algoritmos.tec.Json;
import progra.algoritmos.tec.ListController;
import progra.algoritmos.tec.Main;
import progra.algoritmos.tec.estructurasDatos.ListFactory;
import progra.algoritmos.tec.estructurasDatos.ListTypes;
import progra.algoritmos.tec.estructurasDatos.Lista;
import progra.algoritmos.tec.estructurasDatos.Nodo;



/**
 * Crea una nueva ventana segun lo que se le solicite
 * @author Dario
 *
 */
public class NewScene{
	/**
	 * 
	 * @param width ancho de la ventana
	 * @param high alto de la ventana
	 * @param name corresponde a si va a crear una ventana para Store, Json o instancia
	 * @param treename arbol que se modifica
	 * @throws IOException en caso de no encontrar un archivo
	 */
	public static void getScene(int width,int high, String name,TreeItem<String> treename) throws IOException {
	        Stage stage = new Stage();
	        stage.centerOnScreen();
	        stage.setTitle("Nuevo "+ name);
	        stage.setWidth(width);
	        stage.setHeight(high);
	        GridPane grid= new GridPane();
	        grid.setPadding(new Insets(10, 10, 10, 10));
	        grid.setVgap(8);
	        grid.setHgap(10);
	        if(name.equals("Json")) {
	        	Label nombre= new Label("Nombre");
	        	Label cantidadColum=new Label("Cantidad de columnas");
	        	TextField contenidoN= new TextField();
	        	Spinner<Integer> intSpinner = new Spinner<>(1, 1000, 0, 1);
	        	GridPane.setConstraints(nombre, 0, 0);
	        	GridPane.setConstraints(cantidadColum, 0, 1);
	        	GridPane.setConstraints(contenidoN, 1, 0);
	        	GridPane.setConstraints(intSpinner, 1, 1);
	        	Button aceptar=new Button("Aceptar");
	        	GridPane.setConstraints(aceptar, 1, 2);
	        	grid.getChildren().addAll(nombre,cantidadColum,contenidoN,intSpinner,aceptar);
	        	Scene esco= new Scene(grid,width,high);
	    		aceptar.setOnAction(event -> {
	    		    Tree.makeBranch(contenidoN.getText(), treename);
	    		    int numColumnas=intSpinner.getValue();
	    		    String Jname=contenidoN.getText();
	    	        int index=1;
	    	    	ListController.search(Main.linkedDB,treename.getValue()).add(Jname);
	    		    while(index<=numColumnas) {
		    		    Stage stage2 = new Stage();
		    	        stage2.centerOnScreen();
		    	        stage2.setWidth(width+50);
		    	        stage2.setHeight(high+100);
	    		    	stage2.setTitle("Columna # "+ index);
	    		    	GridPane grid2= new GridPane();
	    		        grid2.setPadding(new Insets(10, 10, 10, 10));
	    		        grid2.setVgap(8);
	    		        grid2.setHgap(10);
    				  	Label Nombre=new Label("Dato");
    		        	TextField NombreC= new TextField();
    		          	Label Tipo=new Label("Tipo");
    		        	TextField TipoC= new TextField();
    		          	Label Predeterminado=new Label("Predeterminado");
    		        	TextField PredeterminadoC= new TextField();
    		          	Label Requerido=new Label("Requerido");
    		        	TextField RequeridoC= new TextField();
    		          	Label Especial=new Label("Especial");
    		        	TextField EspecialC= new TextField();
    		        	Button agregar=new Button("Agregar");
    		        	GridPane.setConstraints(Nombre, 0, 0);
    		        	GridPane.setConstraints(Tipo, 0, 1);
    		        	GridPane.setConstraints(Predeterminado, 0, 2);
    		        	GridPane.setConstraints(Requerido, 0, 3);
    		        	GridPane.setConstraints(Especial, 0, 4);
    		        	GridPane.setConstraints(NombreC, 1, 0);
    		        	GridPane.setConstraints(TipoC, 1, 1);
    		        	GridPane.setConstraints(PredeterminadoC, 1, 2);
    		        	GridPane.setConstraints(RequeridoC, 1, 3);
    		        	GridPane.setConstraints(EspecialC, 1, 4);
    		        	GridPane.setConstraints(agregar, 1, 5);
    		        	grid2.getChildren().addAll(Nombre,NombreC,Tipo,TipoC,Predeterminado,PredeterminadoC,Requerido,RequeridoC,Especial,EspecialC,agregar);
    		        	Scene esco2= new Scene(grid2,width,high);
    		        	stage2.setScene(esco2);
    		        	stage2.show();
	    				agregar.setOnAction(events -> {
	    					ListController.search(Main.linkedDB,Jname).addColumna(NombreC.getText(), TipoC.getText(), PredeterminadoC.getText(),RequeridoC.getText(),EspecialC.getText());	
	    					stage2.close();
	    				});
	    				index++;
	    				stage.close();
	    		    }
	    		    
	    		});
	 	        stage.setScene(esco);
	        }
	        else if(name.equals("Store")){
	        	Label Snombre=new Label("Nombre");
	        	TextField contenidoN= new TextField();
	        	GridPane.setConstraints(Snombre, 0, 0);
	        	GridPane.setConstraints(contenidoN, 1, 0);
	        	Button aceptar=new Button("Aceptar");
	        	GridPane.setConstraints(aceptar, 1, 1);
	        	grid.getChildren().addAll(Snombre,contenidoN,aceptar);
	        	Scene esco= new Scene(grid,width,high);
	    		aceptar.setOnAction(event -> {
	    		    Tree.makeBranch(contenidoN.getText(), Tree.treeF);
	    			Main.linkedDB.add(FileFactory.getFile(contenidoN.getText(), FilesTypes.JsonStore));
	    			stage.close();
	    			
	    		});
    		stage.setScene(esco);
	        	
	        }
	        else if(name.equals("Borrar")) {
	        	Spinner<Integer> intSpinner = new Spinner<>(1, 1000, 0, 1);
	        	Label Snombre=new Label("Numero de Objeto a Borrar");
	        	GridPane.setConstraints(intSpinner, 1, 0);
	        	GridPane.setConstraints(Snombre, 0, 0);
	        	Button aceptar=new Button("Aceptar");
	        	GridPane.setConstraints(aceptar, 0,1);
	        	grid.getChildren().addAll(Snombre,intSpinner,aceptar);
	        	Scene esco= new Scene(grid,width,high);
	    		aceptar.setOnAction(event -> {
	    			int pos=0;
	    			int borrar=intSpinner.getValue();
	    			Lista<Lista<String>>instancias=ListController.search(Main.linkedDB,treename.getValue()).getInstancias();
	    			Nodo<Lista<String>> Instancia=instancias.getHead();
	    			try {
		    			while(pos!=borrar-1) {
		    				Instancia=Instancia.next;
		    				pos++;
		    			}
		    			instancias.delete(Instancia.getValor());
		     			ListController.search(Main.linkedDB,treename.getValue()).setInstancias(instancias);
		     			Json json=ListController.search(Main.linkedDB,treename.getValue());
		    			Table.makeTable(Main.tabla, json);
		    			stage.close();
	    			}
	    			catch (Exception e) {
	    				AlertBox("ERROR","No existe ese Objeto");
					}
	    		});
	    		stage.setScene(esco);
	    		Json json=ListController.search(Main.linkedDB,treename.getValue());
				Table.makeTable(Main.tabla, json);
	        }
	        else if(name.equals("Actualizar")) {
	        	Spinner<Integer> intSpinner = new Spinner<>(1, 1000, 0, 1);
	        	Label Snombre=new Label("Numero de Objeto a Actualizar");
	        	GridPane.setConstraints(intSpinner, 1, 0);
	        	GridPane.setConstraints(Snombre, 0, 0);
	        	Button aceptar=new Button("Aceptar");
	        	GridPane.setConstraints(aceptar, 0,1);
	        	grid.getChildren().addAll(Snombre,intSpinner,aceptar);
	        	Scene esco= new Scene(grid,width,high);
	    		aceptar.setOnAction(event -> {
	    	    	GridPane grid2= new GridPane();
    		        grid2.setPadding(new Insets(10, 10, 10, 10));
    		        grid2.setVgap(8);
    		        grid2.setHgap(10);
	    			int pos=0;
	    			int borrar=intSpinner.getValue();
	    			Lista<Lista<String>>instancias=ListController.search(Main.linkedDB,treename.getValue()).getInstancias();
	    			Nodo<Lista<String>> Instancia=instancias.getHead();
	    			try {
		    			while(pos<borrar-1) {
		    				Instancia=Instancia.next;
		    				pos++;
		    			}
	    			}
	    			catch (Exception e) {
	    				AlertBox("ERROR","No existe ese Objeto");
	    				return;
						}
	    			Lista<String>cambio=Instancia.getValor();
	    			Stage stage2=new Stage();
	    			stage2.setWidth(width);
	    			stage2.setHeight(high+150);
		        	Lista<Lista<String>>columnas;
		        	Lista<String>Valores=ListFactory.getlist(ListTypes.Simple);
		        	columnas=ListController.search(Main.linkedDB,treename.getValue()).getColumnas();
		        	Nodo<Lista<String>>temp=columnas.getHead();
		        	Lista<TextField>cajas=ListFactory.getlist(ListTypes.Simple);
		        	while(temp!=null) {
		        		Valores.add(temp.getValor().getHead().getValor());
		        		temp=temp.next;
		        	}
		        	Nodo<String> inicio=Valores.getHead();
		        	int index=0;
		        	temp=columnas.getHead();
		        	Nodo<String>Insta=Instancia.getValor().getHead();
		        	while(inicio!=null) {
		        		Label Snombre2=new Label(inicio.getValor());
			        	TextField contenidoN2= new TextField(Insta.getValor());
			        	GridPane.setConstraints(Snombre2, 0, index);
			        	GridPane.setConstraints(contenidoN2, 1, index);
			        	grid2.getChildren().addAll(Snombre2,contenidoN2);
			        	cajas.add(contenidoN2);
			        	inicio=inicio.next;
			        	temp=temp.next;
			        	Insta=Insta.next;
			        	index++;
			        	
		        	}
		        	Button aceptar2=new Button("Aceptar");
		        	GridPane.setConstraints(aceptar2, 1, index+1);
		        	grid2.getChildren().add(aceptar2);
		    		aceptar2.setOnAction(event2 -> {
		    			Nodo<TextField>caja=cajas.getHead();
		    			Lista<String>datos=ListFactory.getlist(ListTypes.Simple);
		    			while(caja!=null) {
		    				datos.add(caja.getValor().getText());
		    				caja=caja.next;
		    			}
		    			datos.print();
		    			cambio.setLista(datos);
		    			Json json=ListController.search(Main.linkedDB,treename.getValue());
		    			Table.makeTable(Main.tabla, json);
		    			stage2.close();
		    		});
		    		ScrollPane scroll2=new ScrollPane(grid2);
		    		Scene esco2= new Scene(scroll2,width-10,high);
		    		stage.close();
		    		stage2.setScene(esco2);
		    		stage2.show();
	    		});
	    		stage.setScene(esco);
	        	
	        	
	        }
	        else if(name.equals("Buscar")) {
	        	Label Snombre=new Label("Buscar");
	        	TextField contenidoN= new TextField();
	        	Label en=new Label("Atributo");
	        	ComboBox<String>combo=new ComboBox<>();
	        	Lista<Lista<String>>columnas= ListController.search(Main.linkedDB,treename.getValue()).getColumnas();
	        	Nodo<Lista<String>>ini=columnas.getHead();
	        	while(ini!=null) {
	        		combo.getItems().add(ini.getValor().getHead().getValor());
	        		ini=ini.next;
	        	}
	        	GridPane.setConstraints(Snombre, 0, 0);
	        	GridPane.setConstraints(contenidoN, 1, 0);
	        	GridPane.setConstraints(en, 0, 1);
	        	GridPane.setConstraints(combo, 1,1);
	        	Button aceptar=new Button("Aceptar");
	        	GridPane.setConstraints(aceptar, 1, 2);
	        	grid.getChildren().addAll(Snombre,contenidoN,aceptar,en,combo);
	        	Scene esco= new Scene(grid,width,high);
	    		aceptar.setOnAction(event -> {
	    			Json json=ListController.search(Main.linkedDB, treename.getValue());
	    			int index=combo.getSelectionModel().getSelectedIndex();
	    			String buscado=contenidoN.getText();
	    			Table.Buscado(Main.tabla,json, index, buscado);
	    			stage.close();
	    		});
	    		stage.setScene(esco);
	        	
	        }
	        else {
	        	Lista<Lista<String>>columnas;
	        	Lista<String>Valores=ListFactory.getlist(ListTypes.Simple);
	        	columnas=ListController.search(Main.linkedDB,treename.getValue()).getColumnas();
	        	Nodo<Lista<String>>temp=columnas.getHead();
	        	Lista<TextField>cajas=ListFactory.getlist(ListTypes.Simple);
	        	while(temp!=null) {
	        		Valores.add(temp.getValor().getHead().getValor());
	        		temp=temp.next;
	        	}
	        	Nodo<String> inicio=Valores.getHead();
	        	int index=0;
	        	temp=columnas.getHead();
	        	while(inicio!=null) {
	        		Label Snombre=new Label(inicio.getValor());
		        	TextField contenidoN= new TextField(temp.getValor().getHead().next.next.next.next.getValor());
		        	GridPane.setConstraints(Snombre, 0, index);
		        	GridPane.setConstraints(contenidoN, 1, index);
		        	grid.getChildren().addAll(Snombre,contenidoN);
		        	cajas.add(contenidoN);
		        	if(validar(temp.getValor())) {
		        		Label req=new Label("*");
		        		GridPane.setConstraints(req,2,index);
		        		grid.getChildren().add(req);
		        	}
		        	inicio=inicio.next;
		        	temp=temp.next;
		        	index++;
		        	
	        	}
	        	Button aceptar=new Button("Aceptar");
	        	GridPane.setConstraints(aceptar, 1, index+1);
	        	grid.getChildren().add(aceptar);
	    		aceptar.setOnAction(event -> {
	    			boolean verdad=true;
	    			Nodo<TextField>caja=cajas.getHead();
	    			Lista<String>datos=ListFactory.getlist(ListTypes.Simple);
	    			Nodo<Lista<String>> valid=columnas.getHead();
	    			while(caja!=null) {
	    				String dato=caja.getValor().getText();
	    				if(validar(valid.getValor())) {
	    					System.out.println(dato);
	    					System.out.println("arriba");
	    					if(dato.length()!=0) {
	    						datos.add(dato);
	    					}
	    					else {
	    						verdad=false;
	    				}
	    			}
	    				else {
    						datos.add(dato);
	    				}
	    				
	    			valid=valid.next;
	    			caja=caja.next;
	    			}
	    			datos.print();
	    			if(verdad) {
		    			ListController.search(Main.linkedDB,treename.getValue()).addInstance(datos);
		    			Json json=ListController.search(Main.linkedDB,treename.getValue());
		    			Table.makeTable(Main.tabla, json);
		    			stage.close();
	    			}
	    			else {
	    				AlertBox("ERROR", "Un dato es Requerido");
	    			}
	    			
	    		});
	    		ScrollPane scroll=new ScrollPane(grid);
	    		Scene esco= new Scene(scroll,width,high);
	    		stage.setScene(esco);

	        }
	        stage.show();	
	}
	public static void AlertBox(String titulo,String mensaje) {
		Stage window= new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(titulo);
		window.setMinWidth(250);
		window.setMinHeight(100);
		Label label =new Label(mensaje);
		Button cerrar= new Button("Cerrar");
		cerrar.setOnAction(e -> window.close());
		VBox box=new VBox(10);
		box.setAlignment(Pos.CENTER);
		box.getChildren().addAll(label,cerrar);
		Scene scene= new Scene(box);
		window.setScene(scene);
		window.showAndWait();
	}
	public static boolean validar(Lista<String> columna) {
		Nodo<String>valor=columna.getHead().next.next.next;
		if(valor.getValor().equalsIgnoreCase("si")) {
			return true;
		}
		else {
			return false;
		}
	}
}
