package rendering;

import game.engine.DrawObject;
import game.engine.Drawable;
import javafx.geometry.Point2D;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Circle;

public class DrawCircle implements Drawable {

	private double anchorX;
    private double anchorY;
    private double orgTranslateX;
    private double orgTranslateY;
    
	@Override
	public void draw(DrawObject drawMe, FlowPane gameFlow) {
		Point2D objectPosition = drawMe.getPosition();
		Point2D objectDimensions = drawMe.getDimensions();

		Circle circle = new Circle(objectPosition.getX(), objectPosition.getY(), objectDimensions.getX(),
				drawMe.getColor());
		
		circle.setTranslateX(objectPosition.getX());
		circle.setTranslateY(objectPosition.getY());

		circle.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            
            orgTranslateX = ((Circle) (event.getSource())).getTranslateX();
			orgTranslateY = ((Circle) (event.getSource())).getTranslateY();

			((Circle) (event.getSource())).toFront();
        });
		circle.setOnMouseDragged(event -> {
			double offsetX = event.getSceneX() - anchorX;
			double offsetY = event.getSceneY() - anchorY;
			double newTranslateX = orgTranslateX + offsetX;
			double newTranslateY = orgTranslateY + offsetY;

			((Circle) (event.getSource())).setTranslateX(newTranslateX);
			((Circle) (event.getSource())).setTranslateY(newTranslateY);
        });

		gameFlow.getChildren().add(circle);

	}

}
