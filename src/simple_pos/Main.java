package simple_pos;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {
	//GUI Components
	static AnchorPane root;	
	protected static List<AnchorPane> grid = new ArrayList<AnchorPane>();	
	protected static int CurrentPaneIndex=0;
	
	//App
	private static Management management;

	@Override
	public void start(Stage primaryStage) {
		try {
			root = (AnchorPane)FXMLLoader.load(getClass().getResource("GUIAnchor.fxml"));
			
			grid.add((AnchorPane)FXMLLoader.load(getClass().getResource("GUILogin.fxml")));
			
			
			root.getChildren().add(grid.get(0));
						
			Scene scene = new Scene(root,800,600);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setTitle("Simple POS");
			primaryStage.setScene(scene);
		    primaryStage.setWidth(800);
		    primaryStage.setHeight(600);
			primaryStage.setResizable(false);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setPane(int NewPaneIndex) {
		root.getChildren().remove(grid.get(CurrentPaneIndex));
		root.getChildren().add(grid.get(NewPaneIndex));
		CurrentPaneIndex=NewPaneIndex;
	}

	public static void main(String[] args) {
		management=new Management();
		launch(args);
	}

	public static Management getManagement() {
		return management;
	}
	
	public static void receiptPopup(){
		Stage receiptStage = new Stage();
		receiptStage.setTitle("Receipt");
		VBox comp = new VBox();
		TextArea receipt = new TextArea();
		receipt.setEditable(false);
		receipt.setPrefHeight(600);
		comp.getChildren().add(receipt);
		Scene stageScene = new Scene(comp, 300, 600);
		receiptStage.setScene(stageScene);
		receiptStage.show();
		}
}
