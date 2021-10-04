package game.engine;

import javafx.scene.layout.FlowPane;

//Drawable interface
public interface Drawable {
  //Method that will draw drawMe onto context
	public void draw(GameObject drawMe, FlowPane gameFlow);
}
