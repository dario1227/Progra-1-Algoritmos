package progra.algoritmos.tec.GUI;
import java.io.IOException;
import org.json.simple.parser.ParseException;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import progra.algoritmos.tec.ListController;
import progra.algoritmos.tec.Main;
import progra.algoritmos.tec.estructurasDatos.Lista;
import progra.algoritmos.tec.estructurasDatos.Nodo;
import progra.algoritmos.tec.jsonController.Stores;
public class Tree extends TreeCell<String>  {
	public static TreeItem<String>treeF;
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

	public static TreeItem<String> makeBranch(String store, TreeItem<String> tree) {
		TreeItem<String> item= new TreeItem<String>(store);
		tree.getChildren().add(item);
		return item;
	}
	public ContextMenu getMenu(int num) {
		ContextMenu menuStore= new ContextMenu();
		ContextMenu menuJson=new ContextMenu();
		MenuItem op1= new MenuItem("Agregar Json");
		MenuItem opS=new MenuItem("Eliminar store");
		MenuItem op2= new MenuItem("Mostrar Datos");
		MenuItem op3= new MenuItem("Agregar Objeto");
		MenuItem op4= new MenuItem("Eliminar Json");
		MenuItem op5= new MenuItem("Eliminar Objeto");
		MenuItem op6= new MenuItem("Buscar Objeto");
		MenuItem op7= new MenuItem("Actualizar");
		op1.setOnAction(event -> {
		    try {
				NewScene.getScene(300, 300,"Json",getTreeItem());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		});
		op2.setOnAction(event -> {
		    System.out.println("Fuck man, this tutorial is already outdated as fuck");
		});
		op3.setOnAction(event -> {
			try {
				NewScene.getScene(300, 300,"objeto",getTreeItem());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
		    ;
		});
		op4.setOnAction(event -> {
			TreeItem<String>parent=getTreeItem().getParent();
			parent.getChildren().remove(getIndex()-1);
		    ListController.deleteJ(Main.linkedDB, getTreeItem().getValue(),parent.getValue());
		});
		op5.setOnAction(event -> {
		    System.out.println("Fuck man, this tutorial is already outdated as fuck");
		});
		op6.setOnAction(event -> {
		    System.out.println("Fuck man, this tutorial is already outdated as fuck");
		});
		op7.setOnAction(event -> {
		    System.out.println("Fuck man, this tutorial is already outdated as fuck");
		});
		opS.setOnAction(event -> {
			Tree.treeF.getChildren().remove(getIndex());
		    ListController.delete(Main.linkedDB, getTreeItem().getValue());
		});
		menuStore.getItems().addAll(op1,opS);
		menuJson.getItems().addAll(op2,op3,op4,op5,op6,op7);
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
