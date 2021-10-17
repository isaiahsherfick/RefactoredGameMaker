//@Author Christian Dummer
package views;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import sprite.Sprite;

public class PlayerView {

	private Scene playerScene;
	private Stage playerStage;
	private View view;

	//Controls for elements in PlayerView.fxml
	@FXML
	private Button playStopButton;
	@FXML
	private Button undoPauseButton;
	@FXML
	private Button saveButton;
	@FXML
	private Button loadButton;
	@FXML 
	private Canvas gameCanvas;
	public PlayerView(View v) {
		try {
			view = v;
			//Loads and shows the playerView
			playerStage = new Stage();
			playerStage.setTitle("Player View");
			FXMLLoader playerWindowLoader = new FXMLLoader();
			playerWindowLoader.setLocation(View.class.getResource("PlayerView.fxml"));
			playerWindowLoader.setController(this);
			AnchorPane playerLayout = (AnchorPane)playerWindowLoader.load();
			playerScene = new Scene(playerLayout);
			playerStage.setScene(playerScene);
			playerStage.setX(905);
			playerStage.setY(50);
		}
		catch(IOException ioe) {
			System.out.println("Failed to load PlayerView");
		}
	}
	
	public void showPlayer()
	{
		playerStage.show();
	}

	public Scene getScene() {
		return this.playerScene;
	}

	public Stage getStage() {
		return this.playerStage;
	}
	
	public void setGameCanvas(Canvas c) {
		this.gameCanvas = c;
	}
	
	public Canvas getGameCanvas() {
		return this.gameCanvas;
	}

			// Event Listener on Button[#playStopButton].onAction
			@FXML
			public void playStopButtonClicked(ActionEvent event) {
				if(playStopButton.getText().equals("Play")) {
					//If play is pressed, switch buttons to Play Context
					playStopButton.setText("Stop");
					undoPauseButton.setText("Pause");
					saveButton.setVisible(false);
					saveButton.setDisable(true);
					loadButton.setVisible(false);
					loadButton.setDisable(true);
					
					view.getMakerView().getTabPane().setDisable(true);;
					view.getController().play();
				}
				else if(playStopButton.getText().equals("Stop")) {
					//If Stop is pressed, switch buttons back to maker context
					playStopButton.setText("Play");
					undoPauseButton.setText("Undo");
					saveButton.setVisible(true);
					saveButton.setDisable(false);
					loadButton.setVisible(true);
					loadButton.setDisable(false);

					view.getMakerView().getTabPane().setDisable(false);
					view.getController().stop();
				}
			}
			
			
			
			// Event Listener on Button[#undoPauseButton].onAction
			@FXML
			public void undoPauseButtonClicked(ActionEvent event) 
			{
				if(undoPauseButton.getText().equals("Undo")) {
					view.getController().undo();
				}
				else if(undoPauseButton.getText().equals("Pause")) {
					view.getController().pause();
					undoPauseButton.setText("Resume");
				}
				else if(undoPauseButton.getText().equals("Resume")) {
					view.getController().resume();
					undoPauseButton.setText("Pause");
				}
				
			}
			// Event Listener on Button[#saveButton].onAction
			@FXML
			public void saveButtonClicked(ActionEvent event) {
				view.getController().save();
			}
			// Event Listener on Button[#loadButton].onAction
			@FXML
			public void loadButtonClicked(ActionEvent event) {
				view.getController().load();
				view.setCurrentlySelectedSprite(view.getController().getSpriteList().get(view.getController().getSpriteList().size() - 1));
			}
			
			//On the canvas clicked, check to see if the click intersects with a sprite's hitbox, and if so make it the currently
			//selected Sprite.
			@FXML
			public void canvasClicked(MouseEvent event) {
					double clickedX = event.getX();
					double clickedY = event.getY();
					for(Sprite s: view.getController().getSpriteList()) {
						if(clickedX >= s.getHitBox().getTopLeft().getX() && clickedY >= s.getHitBox().getTopLeft().getY()) {
							if(clickedX <= s.getHitBox().getBottomRight().getX() && clickedY <= s.getHitBox().getBottomRight().getY()) {
								//If click is within the hitbox, then make it currently selected sprite and adjust the properties pane;
								view.setCurrentlySelectedSprite(s);
								view.getMakerView().setPanesForCurrentlySelectedSprite();
							}
						}
					}
				
			}
			
			public void clearCanvas()
			{
				gameCanvas.getGraphicsContext2D().setFill(Color.WHITE);
				gameCanvas.getGraphicsContext2D().fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
			}
			
			//When the canvas is dragged, get the sprite and adjust it's x/y
			@FXML 
			public void canvasDragged(MouseEvent event) {
				//TODO This is inefficient, but without a check for the new currently selected sprite it bugs out
				
					canvasClicked(event);
					Sprite currentlySelectedSprite = view.getCurrentlySelectedSprite();
					//Get the events x/y and set it to the sprite
					double newX = event.getX() - (currentlySelectedSprite.getAppearance().getWidth() * .5);
					double newY = event.getY() - (currentlySelectedSprite.getAppearance().getHeight() * .5);
					currentlySelectedSprite.setX(newX);
					currentlySelectedSprite.setY(newY);
					view.drawAllExcept(currentlySelectedSprite.getSpriteId());
					currentlySelectedSprite.draw(gameCanvas.getGraphicsContext2D());
				
			}
			
			//When the canvas is released, update the sprite
			@FXML
			public void canvasReleased(MouseEvent event)
			{
					Sprite currentlySelectedSprite = view.getCurrentlySelectedSprite();
					view.getController().modifySprite(currentlySelectedSprite);
			}
			
	
}
