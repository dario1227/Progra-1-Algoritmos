package progra.algoritmos.tec.GUI;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class Layout{
	public static Scene getScene(int width,int high) {
		StackPane layout= new StackPane();
		Scene scene= new Scene(layout, width, high);
		return scene;
		
		
	}


}
