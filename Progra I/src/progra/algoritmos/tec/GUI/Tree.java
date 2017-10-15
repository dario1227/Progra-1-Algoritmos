package progra.algoritmos.tec.GUI;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import progra.algoritmos.tec.Json;
import progra.algoritmos.tec.ListController;
import progra.algoritmos.tec.Main;
import progra.algoritmos.tec.estructurasDatos.Lista;
import progra.algoritmos.tec.estructurasDatos.Nodo;
import progra.algoritmos.tec.jsonController.Stores;
/**
 * Crea un Arbol con el nombre de los archivos
 * @author Dario
 *
 */
public class Tree extends TreeCell<String>  {
	public static TreeItem<String>treeF;
	/*
	 * Actualiza la variable static treeF
	 */
	public static void newTree() throws IOException, ParseException {
		TreeItem<String> tree= new TreeItem<String>();
		Lista<Lista<String>> stores=Stores.getAllStores();
		Nodo<Lista<String>> inicio=stores.getHead();
		while(inicio!=null) {
			Nodo<String>Store=inicio.getValor().getHead();
			Nodo<String>json=Store.next;
			String name=Store.getValor();
			TreeItem<String> ini=makeBranch(name,tree);
			while(json!=null) {
				makeBranch(json.getValor().substring(0, json.getValor().length()-5), ini);
				json=json.next;
			}
			inicio=inicio.next;
		}
		 treeF=tree;
	}
/**
 * Crea un hijo al tree indicado
 * @param store nombre del hijo
 * @param tree padre
 * @return
 */
	public static TreeItem<String> makeBranch(String store, TreeItem<String> tree) {
		TreeItem<String> item= new TreeItem<String>(store);
		tree.getChildren().add(item);
		return item;
	}
	public ContextMenu getMenu(int num) {
		ContextMenu menuStore= new ContextMenu();
		ContextMenu menuJson=new ContextMenu();
		MenuItem op1= new MenuItem("Agregar Json");
		MenuItem opA= new MenuItem("Borrar Objeto");
		MenuItem opB= new MenuItem("Actualizar");
		MenuItem opS=new MenuItem("Eliminar store");
		MenuItem op2= new MenuItem("Mostrar Datos");
		MenuItem op3= new MenuItem("Agregar Objeto");
		MenuItem op4= new MenuItem("Eliminar Json");
		MenuItem op6= new MenuItem("Buscar Objeto");
		op1.setOnAction(event -> {
		    try {
				NewScene.getScene(300, 150,"Json",getTreeItem());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		});
		opB.setOnAction(event -> {
			try {
				NewScene.getScene(350, 150,"Actualizar",getTreeItem());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		opA.setOnAction(event -> {
			try {
				NewScene.getScene(350, 150,"Borrar",getTreeItem());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		op2.setOnAction(event -> {
			Json json=ListController.search(Main.linkedDB,getTreeItem().getValue());
			Table.makeTable(Main.tabla, json);
		});
		op3.setOnAction(event -> {
			try {
				NewScene.getScene(300, 300,"objeto",getTreeItem());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		op4.setOnAction(event -> {
			TreeItem<String>parent=getTreeItem().getParent();
			parent.getChildren().remove(getTreeItem());
		    ListController.deleteJ(Main.linkedDB, getTreeItem().getValue(),parent.getValue());
		});

		op6.setOnAction(event -> {
			try {
				NewScene.getScene(300, 150,"Buscar",getTreeItem());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

		opS.setOnAction(event -> {
			Tree.treeF.getChildren().remove(getTreeItem());
		    ListController.delete(Main.linkedDB, getTreeItem().getValue());
		});
		menuStore.getItems().addAll(op1,opS);
		menuJson.getItems().addAll(op2,op3,op4,op6,opB,opA);
		ContextMenu menu;
		
		if(num==1) {
			menu=menuStore;
		}
		else {
			menu=menuJson;
		}
		return menu;
		
	}
	 @Override
	 /**
	  * Actualiza el menu de cada tree segun si es store o Json
	  */
	    public void updateItem(String item, boolean empty) {
	        super.updateItem(item, empty);

	        if (empty) {
	            setText(null);
	            setGraphic(null);
	        } else {
	            setText(getItem() == null ? "" : getItem().toString());
	            setGraphic(getTreeItem().getGraphic());
	            if(getTreeItem().getParent().getParent()==null) {
	            	setContextMenu(getMenu(1));
	            }
	            else {
	            	setContextMenu(getMenu(2));
	            }
	            
	        }
	    }
}
