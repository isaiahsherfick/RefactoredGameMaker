package game.engine;

import javafx.scene.canvas.GraphicsContext;

//Drawable interface
public interface Drawable {
  //Method that will draw drawMe onto context
	public void draw(DrawObject drawMe, GraphicsContext context);
}
