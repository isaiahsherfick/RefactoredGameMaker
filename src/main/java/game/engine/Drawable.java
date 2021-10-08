package game.engine;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;

//Drawable interface
public interface Drawable 
{
	//Tell the drawable to draw itself on g
	public void draw(GraphicsContext g);

	//DEPRECATED - delete after sprite can get rid of it
	public void draw(Sprite sprite, FlowPane gameFlow);
}
