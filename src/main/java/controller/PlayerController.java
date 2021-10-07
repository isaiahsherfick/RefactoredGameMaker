package controller;

import game.engine.Drawable;
import game.engine.GameObject;
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
	public PlayerController() {
		gameModel = new GameModel();
	}

	public void addNewGameObject(String selectedShape, ImageView imageView, String objectName, String color) {
		if (imageView.getImage() == null) {
			GameObject gameObject = new GameObject(objectName, getDrawableBehaviour(selectedShape),
					Color.valueOf(color), new Point2D(10, 10), new Point2D(50, 25));
			
			gameModel.addNewGameObject(gameObject);
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
