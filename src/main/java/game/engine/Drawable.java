package game.engine;

import javafx.scene.layout.FlowPane;

//Drawable interface
public interface Drawable {
  //Method that will draw drawMe onto context
	
	//TODO
	//I feel like it's probably better to simply have 
	//draw(GraphicsContext2D g) {...}
	//So that the call looks like "GameObject.draw()"
	//Isaiah
	public void draw(Sprite drawMe, FlowPane gameFlow);
}
