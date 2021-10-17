//@Author Christian Dummer
package views;

import java.util.ArrayList;
import controller.Controller;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;
import sprite.NullSprite;
import sprite.Sprite;
import javafx.scene.paint.Color;

public class View implements Observer
{
		private Controller controller;
		private Sprite currentlySelectedSprite;
		private PlayerView playerView;
		private MakerView makerView;
		private boolean playingGame;

		//Displays both views, called by Main.java when program is launched.
		public View(Stage primaryStage) {
			this.makerView = new MakerView(primaryStage, this);
			this.playerView = new PlayerView(this);
			//By default a null sprite
			currentlySelectedSprite = new NullSprite();
		}
		
		//Just for unit tests
		public View()
		{
			playerView.setGameCanvas(new Canvas());
		}
		
		public PlayerView getPlayerView() {
			return this.playerView;
		}
		
		public MakerView getMakerView() {
			return this.makerView;
		}
		
		public boolean getPlayingGame() {
			return this.playingGame;
		}
		
		public void playOrStopGame() {
			playingGame = !playingGame;
		}
		public void showStages()
		{
			this.makerView.showMaker();
			this.playerView.showPlayer();
		}
		
		public void setController(Controller c) {
			this.controller = c;
		}
	
		public Controller getController() {
			return this.controller;
		}
		
		//sets currentlySelectedSprite
		public void setCurrentlySelectedSprite(Sprite s) {
			this.currentlySelectedSprite = s;
		}
		
		public Sprite getCurrentlySelectedSprite() {
			return this.currentlySelectedSprite;
		}
		
		public void modifySpriteCommand() {
			this.controller.modifySprite(currentlySelectedSprite);
		}
	    
	    public void drawAll()
	    {
	    	Canvas gameCanvas = playerView.getGameCanvas();
	    	gameCanvas.getGraphicsContext2D().setFill(Color.WHITE);
	    	gameCanvas.getGraphicsContext2D().fillRect(0, 0, gameCanvas.getWidth(), gameCanvas.getHeight());
	    	ArrayList<Sprite> allSprites = controller.getSpriteList();
	    	for (Sprite s : allSprites)
	    	{
	    		s.draw(gameCanvas.getGraphicsContext2D());
	    	}
	    }
	    
		@Override
		public void update() 
		{
			drawAll();
		}

	

		
}

