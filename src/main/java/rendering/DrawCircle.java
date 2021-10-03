package rendering;

import game.engine.DrawObject;
import game.engine.Drawable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;

public class DrawCircle implements Drawable {
	
	@Override
	public void draw(DrawObject drawMe, GraphicsContext context) {
        Point2D objectPosition = drawMe.getPosition();
        Point2D objectDimensions = drawMe.getDimensions();
        
        context.setFill(drawMe.getColor());
        context.fillOval(objectPosition.getX(), objectPosition.getY(), objectDimensions.getX(), objectDimensions.getY());
	}

}
