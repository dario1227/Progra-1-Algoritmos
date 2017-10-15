package progra.algoritmos.tec.GUI;



import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import progra.algoritmos.tec.Json;
import progra.algoritmos.tec.Main;
import progra.algoritmos.tec.estructurasDatos.ListFactory;
import progra.algoritmos.tec.estructurasDatos.ListTypes;
import progra.algoritmos.tec.estructurasDatos.Lista;
import progra.algoritmos.tec.estructurasDatos.Nodo;

public class Table {
	static int index=0;
	public static void makeTable(HBox tabla,Json json){
		Main.tabla.getChildren().clear();
		Lista<Lista<String>> columnas=json.getColumnas();
		Lista<Lista<String>> instancias=json.getInstancias();
		Nodo<Lista<String>> temp=columnas.getHead();
		Nodo<Lista<String>> instancia=instancias.getHead();
		Text text= new Text("No");
		text.setFont(Font.font("Verdana",16));
		VBox numbers= new VBox(text);
		numbers.getChildren().add(new Text());
		Lista<VBox> datos=ListFactory.getlist(ListTypes.Simple);
		datos.add(numbers);
		int index=1;
		while(temp!=null) {
			VBox dato=new VBox();
			Text texto= new Text(temp.getValor().getHead().getValor());
			texto.setFont(Font.font("Verdana",17));
			dato.getChildren().add(texto);
			dato.getChildren().add(new Text());
			datos.add(dato);
			temp=temp.next;
		}
		while(instancia!=null) {
			Lista<String>dats=instancia.getValor();
			Nodo<String>valor=dats.getHead();
			Nodo<VBox> colu=datos.getHead().next;
			Text numero=new Text(Integer.toString(index));
			numero.setFont(Font.font("Verdana",15));
			datos.getHead().getValor().getChildren().add(numero);
			while(valor!=null) {
				Text texto= new Text(valor.getValor());
				texto.setFont(Font.font("Verdana",15));
				colu.getValor().getChildren().add(texto);
				valor=valor.next;
				colu=colu.next;
			}
			index++;
			instancia=instancia.next;
		}
		
		Nodo<VBox>valor=datos.getHead();
		while(valor!=null) {
			Main.tabla.setSpacing(20);
			Main.tabla.getChildren().add(valor.getValor());
			valor=valor.next;
		}
	}
	public static void Buscado(HBox tabla,Json json,int index,String buscado) {
		Main.tabla.getChildren().clear();
		Lista<Lista<String>> columnas=json.getColumnas();
		Lista<Lista<String>> instancias=json.getInstancias();
		Nodo<Lista<String>> temp=columnas.getHead();
		Nodo<Lista<String>> instancia=instancias.getHead();
		Text text= new Text("No");
		text.setFont(Font.font("Verdana",17));
		VBox numbers= new VBox(text);
		numbers.getChildren().add(new Text());
		Lista<VBox> datos=ListFactory.getlist(ListTypes.Simple);
		datos.add(numbers);
		int num=1;
		int encontrados=0;
		while(temp!=null) {
			VBox dato=new VBox();
			Text texto= new Text(temp.getValor().getHead().getValor());
			texto.setFont(Font.font("Verdana",17));
			dato.getChildren().add(texto);
			dato.getChildren().add(new Text());
			datos.add(dato);
			temp=temp.next;
		}
		while(instancia!=null) {
			int point=0;
			Lista<String>dats=instancia.getValor();
			Nodo<String>valor=dats.getHead();
			Nodo<String>buscad=dats.getHead();
			while(point!=index) {
				buscad=buscad.next;
				point++;
			}
			if(buscad.getValor().equalsIgnoreCase(buscado)) {
				encontrados++;
				Nodo<VBox> colu=datos.getHead().next;
				Text numero=new Text(Integer.toString(num));
				numero.setFont(Font.font("Verdana",15));
				datos.getHead().getValor().getChildren().add(numero);
				while(valor!=null) {
					Text texto= new Text(valor.getValor());
					texto.setFont(Font.font("Verdana",15));
					colu.getValor().getChildren().add(texto);
					valor=valor.next;
					colu=colu.next;
				}
				num++;
				instancia=instancia.next;
			}
			else {
				instancia=instancia.next;
			}
		}
		if(encontrados==0) {
			Text error=new Text("NO SE ENCONTRO EL OBJETO");
			error.setFont(Font.font("Verdana",20));
			Main.tabla.getChildren().add(error);
			
		}
		else {
			Nodo<VBox>valor=datos.getHead();
			while(valor!=null) {
				Main.tabla.setSpacing(20);
				Main.tabla.getChildren().add(valor.getValor());
				valor=valor.next;
			}
		}
		
	}
}
