package rendering;

import game.engine.Drawable;
import game.engine.Sprite;
import javafx.geometry.Point2D;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Circle;

public class DrawCircle implements Drawable {

	private double anchorX;
    private double anchorY;
    private double orgTranslateX;
    private double orgTranslateY;
    
	@Override
	//TODO
	//I now understand the point of draw() having a gameobject get passed to it.
	//We still need to move that behavior into the sprite and have the view call draw() - that will allow the argument to be removed
	//draw() shouldn't update the position of the sprite like it's currently doing
	//separation of concerns 
	//Isaiah
	public void draw(Sprite drawMe, FlowPane gameFlow) {
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
			
			drawMe.setPosition(new Point2D(newTranslateX, newTranslateY));
        });

		gameFlow.getChildren().add(circle);

	}

}
