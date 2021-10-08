package game.engine;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

public class DrawImage implements Drawable {
	
	private double anchorX;
    private double anchorY;
    private double orgTranslateX;
    private double orgTranslateY;

	public void draw(Sprite drawMe, FlowPane gameFlow) {
		Point2D objectPosition = drawMe.getPosition();
		Point2D objectDimensions = drawMe.getDimensions();

		//TODO
		//This is a null pointer hazard, we need to do a null image for all gameobjects
		//Also the name "imageview" implies that this is a "view" which is not how I understand mvc to work
		//isaiah
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
			
			drawMe.setPosition(new Point2D(newTranslateX, newTranslateY));
        });
		
		gameFlow.getChildren().add(imageView);
	}

	@Override
	public void draw(GraphicsContext g) {
		// TODO Auto-generated method stub
		
	}
}
