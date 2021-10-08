package controller;

import game.engine.Drawable;
import game.engine.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import model.GameModel;
import rendering.DrawCircle;
import rendering.DrawSquare;

public class PlayerController {

	private static final String CIRCLE = "CIRCLE";
	private GameModel gameModel;

	//TODO
	//Very confused about how each of these view/controller things create a new GameModel()
	//in their constructors -- they should share one gamemodel
	//isaiah
	//The gameModel also isnt even a model so they all have independent ArrayLists
	public PlayerController() {
		gameModel = new GameModel();
	}

	public void addNewGameObject(String selectedShape, ImageView imageView, String objectName, String color) {
		if (imageView.getImage() == null) {
			Sprite sprite = new Sprite();
			
			gameModel.addNewGameObject(sprite);
		}
	}

	private Drawable getDrawableBehaviour(String selectedShape) {
		if (CIRCLE.equals(selectedShape))
			return new DrawCircle();
		return new DrawSquare();
	}
	
	public GameModel getGameModel() {
		return gameModel;
	}

}
