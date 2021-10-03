package controller;

import java.util.HashMap;
import java.util.Map;

import game.engine.DrawImage;
import game.engine.Drawable;
import game.engine.GameObject;
import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import model.GameModel;
import rendering.DrawCircle;
import rendering.DrawSquare;

public class MakerController {

	private static final String CIRCLE = "CIRCLE";
	private static final String SQUARE = "SQUARE";
	private static final String RECTANGLE = "RECTANGLE";
	private static GameObject currentlySelectedObject;
	private GameModel gameModel;
	
	private Map<String, Point2D> dimensionMap = new HashMap<>();

	public MakerController() {
		gameModel = new GameModel();
		dimensionMap.put(CIRCLE, new Point2D(20, 20));
		dimensionMap.put(SQUARE, new Point2D(30, 30));
		dimensionMap.put(RECTANGLE, new Point2D(50, 25));
	}

	public void addNewGameObject(String selectedShape, ImageView imageView, String objectName, String color) {
		if (imageView.getImage() == null) {
			GameObject gameObject = new GameObject(objectName, getDrawableBehaviour(selectedShape),
					Color.valueOf(color), new Point2D(10, 10), dimensionMap.get(selectedShape));
			
			currentlySelectedObject = gameObject;
			gameModel.addNewGameObject(gameObject);
			MainController.performDraw();
		} else {
			GameObject gameObject = new GameObject(objectName, getDrawableBehaviour(selectedShape),
					Color.valueOf(color), new Point2D(40, 40), new Point2D(60, 80), imageView.getImage());
			
			currentlySelectedObject = gameObject;
			gameModel.addNewGameObject(gameObject);
			MainController.performDraw();
		}
	}

	private Drawable getDrawableBehaviour(String selectedShape) {
		if (CIRCLE.equals(selectedShape))
			return new DrawCircle();
		
		if (SQUARE.equals(selectedShape) || RECTANGLE.equals(selectedShape))
			return new DrawSquare();
		
		return new DrawImage();
	}
	
	public GameModel getGameModel() {
		return gameModel;
	}
	
	public GameObject getCurrentlySelectedObject() {
		return this.currentlySelectedObject;
	}
	
	public void setCurrentlySelectedObject(GameObject o) {
		this.currentlySelectedObject = o;
	}

}
