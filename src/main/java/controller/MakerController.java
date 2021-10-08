package controller;

import java.util.HashMap;
import java.util.Map;

import game.engine.DrawImage;
import game.engine.Drawable;
import game.engine.GameEngine;
import game.engine.Sprite;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import model.GameModel;
import rendering.DrawCircle;
import rendering.DrawSquare;

public class MakerController {
	
	private static double orgSceneX, orgSceneY;
	private static double orgTranslateX, orgTranslateY;

	private static final String CIRCLE = "CIRCLE";
	private static final String SQUARE = "SQUARE";
	private static final String RECTANGLE = "RECTANGLE";
	private static Sprite currentlySelectedObject;
	private GameModel gameModel;
	
	private Map<String, Point2D> dimensionMap = new HashMap<>();

	//TODO
	//All of these controllers are views
	//There is actually 0 MVC in this codebase
	//Isaiah
	public MakerController() {
		gameModel = new GameModel();
		dimensionMap.put(CIRCLE, new Point2D(20, 20));
		dimensionMap.put(SQUARE, new Point2D(30, 30));
		dimensionMap.put(RECTANGLE, new Point2D(50, 25));
	}

	public void addNewGameObject(String selectedShape, ImageView imageView, String objectName, String color) {
		if (imageView.getImage() == null) {
			Sprite sprite = new Sprite();
			
			currentlySelectedObject = sprite;
			GameEngine.sharedInstance.register(currentlySelectedObject);
			sprite.setOnMousePressed(buttonMousePressedEventHandler);
			sprite.setOnMouseDragged(buttonOnMouseDraggedEventHandler);
			gameModel.addNewGameObject(sprite);
			MainController.performDraw(gameModel.getGameObjects());
		} else {
			Sprite sprite = new Sprite();
			
			currentlySelectedObject = sprite;
			GameEngine.sharedInstance.register(currentlySelectedObject);
			gameModel.addNewGameObject(sprite);
			MainController.performDraw(gameModel.getGameObjects());
		}
		//gameModel.getGameObjects().forEach(obj -> GameEngine.sharedInstance.register(obj));
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
	
	public Sprite getCurrentlySelectedObject() {
		return currentlySelectedObject;
	}
	
	public void setCurrentlySelectedObject(Sprite o) {
		currentlySelectedObject = o;
	}
	
	static EventHandler<MouseEvent> buttonMousePressedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			orgSceneX = t.getSceneX();
			orgSceneY = t.getSceneY();
			orgTranslateX = ((Button) (t.getSource())).getTranslateX();
			orgTranslateY = ((Button) (t.getSource())).getTranslateY();

			((Button) (t.getSource())).toFront();
		}
	};

	static EventHandler<MouseEvent> buttonOnMouseDraggedEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent t) {
			double offsetX = t.getSceneX() - orgSceneX;
			double offsetY = t.getSceneY() - orgSceneY;
			double newTranslateX = orgTranslateX + offsetX;
			double newTranslateY = orgTranslateY + offsetY;

			((Button) (t.getSource())).setTranslateX(newTranslateX);
			((Button) (t.getSource())).setTranslateY(newTranslateY);
		}
	};

}
