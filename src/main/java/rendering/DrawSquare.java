package rendering;

import game.engine.DrawObject;
import game.engine.Drawable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class DrawSquare implements Drawable {

	@Override
	public void draw(DrawObject drawMe, GraphicsContext context) {
        Point2D objectPosition = drawMe.getPosition();
        Point2D objectDimensions = drawMe.getDimensions();
        
        context.setFill(drawMe.getColor());
        context.fillRect(objectPosition.getX(), objectPosition.getY(), objectDimensions.getX(), objectDimensions.getY());
	}
}