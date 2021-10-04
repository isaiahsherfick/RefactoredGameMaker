package game.engine;

import javafx.geometry.Point2D;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Circle;

public class DrawImage implements Drawable {
	
	private double anchorX;
    private double anchorY;
    private double orgTranslateX;
    private double orgTranslateY;

	@Override
	public void draw(DrawObject drawMe, FlowPane gameFlow) {
		Point2D objectPosition = drawMe.getPosition();
		Point2D objectDimensions = drawMe.getDimensions();

		ImageView imageView = new ImageView(drawMe.getImage());
		
		imageView.setX(objectPosition.getX());
	    imageView.setY(objectPosition.getY());
		imageView.setFitWidth(objectDimensions.getX());
		imageView.setFitHeight(objectDimensions.getY());
		
		imageView.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            
            orgTranslateX = ((ImageView) (event.getSource())).getTranslateX();
			orgTranslateY = ((ImageView) (event.getSource())).getTranslateY();

			((ImageView) (event.getSource())).toFront();
        });
		imageView.setOnMouseDragged(event -> {
			double offsetX = event.getSceneX() - anchorX;
			double offsetY = event.getSceneY() - anchorY;
			double newTranslateX = orgTranslateX + offsetX;
			double newTranslateY = orgTranslateY + offsetY;

			((ImageView) (event.getSource())).setTranslateX(newTranslateX);
			((ImageView) (event.getSource())).setTranslateY(newTranslateY);
        });
		
		gameFlow.getChildren().add(imageView);
	}
}
