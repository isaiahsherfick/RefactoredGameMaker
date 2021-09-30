package input;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import view.EventMenuItem;

public class ClickPolling {
	
	public static ClickPolling shared = new ClickPolling();
    private static Scene scene;
    private final static HashMap<Double, Double> mouseClickCoordinates = new HashMap<>();
    
    private ClickPolling() {}
    
    public void pollScene(Scene scene) {
        clearKeys();
        removeCurrentKeyHandlers();
        setScene(scene);
    }
    
    private void clearKeys() {
    	mouseClickCoordinates.clear();
    }
    
    private void removeCurrentKeyHandlers() {
        if(scene != null) {
			scene.setOnMouseClicked(null);
		}
	}
    
    private static void setScene(Scene scene) {
    	ClickPolling.scene = scene;
    	ClickPolling.scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				mouseClickCoordinates.put(event.getSceneX(), event.getSceneY());
			}
    	});
    	
	}
    
}
