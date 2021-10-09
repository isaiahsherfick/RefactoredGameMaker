//@Author Christian Dummer
package views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MakerView {
	
	//For now this code is exclusively opening this window, it needs to be created and
	//added to the View.java container that also includes PlayerView
	public static void start(Stage makerStage) {
		try {
			makerStage.setTitle("Maker View");
			FXMLLoader windowLoader = new FXMLLoader();
			windowLoader.setLocation(MakerView.class.getResource("MakerView.fxml"));
			AnchorPane makerLayout = (AnchorPane)windowLoader.load();
			Scene makerScene = new Scene(makerLayout);
			makerStage.setScene(makerScene);
			makerStage.show();
		}
		catch(IOException ex) {
			System.out.println(ex);
		}
		
	}

}
