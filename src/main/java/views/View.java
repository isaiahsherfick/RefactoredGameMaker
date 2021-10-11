//@Author Christian Dummer
package views;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class View {
		//Displays both views
		public static void start(Stage makerStage) {
			try {
				//Loads and shows the makerView
				makerStage.setTitle("Maker View");
				FXMLLoader windowLoader = new FXMLLoader();
				windowLoader.setLocation(View.class.getResource("MakerView.fxml"));
				AnchorPane makerLayout = (AnchorPane)windowLoader.load();
				Scene makerScene = new Scene(makerLayout);
				makerStage.setScene(makerScene);
				makerStage.setX(300);
				makerStage.setY(50);
				makerStage.show();
				
				//Loads and shows the playerView
				Stage playerStage = new Stage();
				playerStage.setTitle("Player View");
				FXMLLoader playerWindowLoader = new FXMLLoader();
				playerWindowLoader.setLocation(View.class.getResource("PlayerView.fxml"));
				AnchorPane playerLayout = (AnchorPane)playerWindowLoader.load();
				Scene playerScene = new Scene(playerLayout);
				playerStage.setScene(playerScene);
				playerStage.setX(905);
				playerStage.setY(50);
				playerStage.show();
				
				

				
			}
			catch(IOException ex) {
				System.out.println(ex);
			}
			
		}
}
