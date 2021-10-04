package rendering;

import game.engine.DrawObject;
import game.engine.Drawable;
import javafx.geometry.Point2D;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class DrawSquare extends Shape implements Drawable {
	
	private double anchorX;
    private double anchorY;
    private double orgTranslateX;
    private double orgTranslateY;

	@Override
	public void draw(DrawObject drawMe, FlowPane gameFlow) {
		Point2D objectPosition = drawMe.getPosition();
		Point2D objectDimensions = drawMe.getDimensions();

		Rectangle rectangle = new Rectangle(objectPosition.getX(), objectPosition.getY(), objectDimensions.getX(),
				objectDimensions.getY());
		
		rectangle.setFill(drawMe.getColor());
		
		rectangle.setTranslateX(objectPosition.getX());
		rectangle.setTranslateY(objectPosition.getY());
		
		rectangle.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            
            orgTranslateX = ((Rectangle) (event.getSource())).getTranslateX();
			orgTranslateY = ((Rectangle) (event.getSource())).getTranslateY();

			((Rectangle) (event.getSource())).toFront();
			
        });
		
		rectangle.setOnMouseDragged(event -> {
			double offsetX = event.getSceneX() - anchorX;
			double offsetY = event.getSceneY() - anchorY;
			double newTranslateX = orgTranslateX + offsetX;
			double newTranslateY = orgTranslateY + offsetY;

			((Rectangle) (event.getSource())).setTranslateX(newTranslateX);
			((Rectangle) (event.getSource())).setTranslateY(newTranslateY);
			
        });
		
		rectangle.setOnDragDone(event -> {
			gameFlow.getChildren().remove((Rectangle) (event.getSource()));
        });
		
		gameFlow.getChildren().add(rectangle);
	}
}