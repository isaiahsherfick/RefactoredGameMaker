package game.engine;

import java.util.ArrayList;
import collisionUtility.CollisionDetection;
import controller.MainController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import pattern.Observable;
import pattern.Observer;
import javafx.util.Duration;

/*
 * Usage: On the Main func, use GameEngine.sharedInstance.setScene(scene) first
 * and then GameEngine.sharedInstance.initiateGameLoop() to start the loop
 */

public class GameEngine implements Observable {

	private ArrayList<Observer> observers;
	
	//TODO
	//The gameEngine should be part of the controller
	//Shouldn't have any kind of JFX stuff ie a Scene -- that's for the view
	//Isaiah
	//Most of this is fine as it just is a timeline producing a tick and updating observers, 
	//However probably don't NEED observer here if its part of a controller communicating with model.
	private static Scene gameScene;
	
	
	private Timeline gameLoop;
	private KeyFrame keyFrame;
	public static GameEngine sharedInstance = new GameEngine();
	// Attempt to achieve 60 FPS
	
	//TODO
	//We could use a Constants.java static class for all magic numbers like this
	//low priority
	//Isaiah
	private final static float framesPerSecond = 0.0165f;
	private double totalTime = 0;
	private double previousTotalTime = 0;
	private double timeDelta = 0;
	private double startNanoTime = System.currentTimeMillis();

	private GameEngine() {
		observers = new ArrayList<Observer>();
		gameLoop = new Timeline();
		gameLoop.setCycleCount(Timeline.INDEFINITE);
		EventHandler<ActionEvent> tick = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent actionEvent) {
				previousTotalTime = totalTime;
				totalTime = (System.currentTimeMillis() - startNanoTime) / 1000.0;
				timeDelta = totalTime - previousTotalTime;
				tick();
			}
		};
		keyFrame = new KeyFrame(Duration.seconds(framesPerSecond), tick);
		gameLoop.getKeyFrames().add(keyFrame);
	}

	@Override
	public void register(Observer observer) {
		// Prevent double registration
		if (!observers.contains(observer)) {
			observers.add(observer);
		}
	}

	@Override
	public void unregister(Observer observer) {
		// Ensure observer is already registered
		int observerIndex = observers.indexOf(observer);
		if (observerIndex >= 0) {
			observers.remove(observerIndex);
		}
	}
	
	@Override
	public void tick() {
		for (Observer observer : observers) {
			observer.update(timeDelta);
		}
		//On each tick check for collision
		MainController.redrawAll();
		CollisionDetection.shared.checkForCollision();
	}

	public void initiateGameLoop() {
		gameLoop.play();
	}
	
	public void stopGameLoop() {
		gameLoop.stop();
	}
	
	public void pauseGameLoop() {
		gameLoop.pause();
	}

	public void setGameScene(Scene gameScene) {
		GameEngine.gameScene = gameScene;
	}

	public Scene getGameScene() {
		return gameScene;
	}

}
