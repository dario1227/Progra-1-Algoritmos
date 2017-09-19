package progra.algoritmos.tec.GUI;
import java.io.IOException;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import progra.algoritmos.tec.FileFactory;
import progra.algoritmos.tec.FilesTypes;
import progra.algoritmos.tec.ListController;
import progra.algoritmos.tec.Main;




public class NewScene{
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
	        	Spinner<Integer> intSpinner = new Spinner<>(1, 100, 0, 1);
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
	        else {
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
	        stage.show();	
	}


}
