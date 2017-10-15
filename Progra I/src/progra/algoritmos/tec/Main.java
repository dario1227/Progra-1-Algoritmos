package progra.algoritmos.tec;
import java.io.IOException;
import org.json.simple.parser.ParseException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import progra.algoritmos.tec.GUI.NewScene;
import progra.algoritmos.tec.GUI.Tree;
import progra.algoritmos.tec.estructurasDatos.Lista;
import progra.algoritmos.tec.jsonController.Instances;
import progra.algoritmos.tec.jsonController.MetadataControl;
/**
 * Main
 * @author Dario
 *
 */
public class Main extends Application implements EventHandler<ActionEvent>{
	public static HBox tabla= new HBox();
	public static Lista<Json> linkedDB;
	public static void main(String[] args) {
		try {
//			Instances.saveInstances(ListController.search(linkedDB,"estudiantesTEc"));
			launch(args);
//			Lista<Json> linkedDB=ListFactory.getlist(ListTypes.Doble);
//			linkedDB.add(FileFactory.getFile("TEC", FilesTypes.JsonStore));
//			linkedDB.add(FileFactory.getFile("UCR", FilesTypes.JsonStore));
//			ListController.search(linkedDB,"TEC").add("EstudiantesTEC");
//			ListController.search(linkedDB,"tec").add("CursosTEC");
//			ListController.search(linkedDB,"ucr").add("EstudiantesUCR");
//			ListController.search(linkedDB,"ucr").add("ProfesoresUCR");
//			ListController.search(linkedDB,"ucr").add("CursosUCR");
//			ListController.search(linkedDB, "estudiantestec").addColumna("Nombre", "string", "", "si", "no");
//			ListController.search(linkedDB, "estudiantestec").addColumna("Carnet", "int", "", "si", "no");
//			ListController.search(linkedDB, "estudiantestec").addColumna("Apellido", "int", "", "si", "no");
//			ListController.search(linkedDB, "estudiantesucr").addColumna("Nombre", "string", "", "si", "no");
//			ListController.search(linkedDB, "Cursostec").addColumna("Codigo", "string", "", "si", "no");
//			ListController.search(linkedDB, "cursosucr").addColumna("ID", "int", "", "si", "no");
//			ListController.search(linkedDB, "profesoresucr").addColumna("Identificacion", "string", "", "si", "no");
//			ListController.search(linkedDB, "estudiantestec").addInstance();
//			ListController.search(linkedDB, "estudiantestec").addInstance();
//			ListController.search(linkedDB, "estudiantestec").addInstance();
//			ListController.search(linkedDB, "estudiantestec").addInstance();
//			ListController.commit(linkedDB);
		//	JsonManagement.saveMetadata(JsonManagement.convertMetadata(linkedDB));
//			JsonManagement.saveInstances(ListController.search(linkedDB, "estudiantestec"));
			
		}
		
		catch (Exception ex) {
			System.out.println(ex);
			System.out.println("SE CAYO ESTA VARA :(");
		}
	}
	/**
	 * Carga JavaFX, el GUI
	 */
	@Override

	public void start(Stage primaryStage) throws Exception {
		linkedDB=MetadataControl.readMetadata();
		Instances.readInstances(linkedDB);
		Tree.newTree();
		primaryStage.setTitle("Base de datos");
		TreeView<String> tree = new TreeView<>(Tree.treeF);
		BorderPane pane= new BorderPane();
		pane.setLeft(tree);
		ContextMenu agregarS= new ContextMenu();
		MenuItem op1= new MenuItem("Agregar JsonStore");
		MenuItem op2= new MenuItem("Commit");
		agregarS.getItems().addAll(op1,op2);
		op1.setOnAction(event -> {
		    try {
				NewScene.getScene(300, 150,"Store",Tree.treeF);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		});
		op2.setOnAction(event -> {
		    try {
				ListController.commit(linkedDB);
				primaryStage.close();
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		tree.setShowRoot(false);
		tree.setContextMenu(agregarS);
	    tree.setCellFactory(new Callback<TreeView<String>,TreeCell<String>>(){
	        @Override
	        public TreeCell<String> call(TreeView<String> p) {
	            return new Tree();
	        }
	    });
	    ImageView imagen=new ImageView(new Image(getClass().getResourceAsStream("tec.jpg"),248,200,false, true));
	   System.out.println(getClass());
	    pane.setBottom(imagen);
	    pane.setLeft(tree);
	    BorderPane padre= new BorderPane();
	    padre.setLeft(pane);
	    ScrollPane scroll=new ScrollPane(tabla);
	    padre.setCenter(scroll);
		Scene scene=new Scene(padre, 900, 600);
		primaryStage.setScene(scene);
		primaryStage.centerOnScreen();
		primaryStage.show();
		System.out.println("ww");

	}
	@Override
	/**
	 * Implementa acciones en la interfaz grafica,eventos del usuario
	 */
	public void handle(ActionEvent event) {
	}
		

}
