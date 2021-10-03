package game.engine;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class DrawImage implements Drawable {

	@Override
	public void draw(DrawObject drawMe, GraphicsContext context) {
		Point2D objectPosition = drawMe.getPosition();
		Point2D objectDimensions = drawMe.getDimensions();

		context.setFill(drawMe.getColor());
		context.drawImage(drawMe.getImage(), objectPosition.getX(), objectPosition.getY(), objectDimensions.getX(),
				objectDimensions.getY());
	}
}
