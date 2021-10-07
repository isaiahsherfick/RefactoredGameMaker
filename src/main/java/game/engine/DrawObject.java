package game.engine;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;
import pattern.Observer;

//TODO
//Why the hell are we extending the JavaFX Shape class? Seems like a lot of overhead
//Also causing me to not be able to compile
//I suggest we ditch the notion of a drawobject entirely and move to a strategy
//Also none of this is saveable, we need a saveable interface and wrapper for all of this junk
//-Isaiah
//Agree, this is a combination of dated code from past projects that has just gotten messier as a result, can be refactored
//to do the same thing but cleaner - Christian
public abstract class DrawObject extends Shape implements Observer {

	// protected CommandListener commandListener;
	protected Drawable drawBehaviour;
	protected Point2D position;
	protected Point2D dimensions;
	protected Color color;
	protected Image image;

	public DrawObject() {
		position = new Point2D(0, 0);
		dimensions = new Point2D(0, 0);
		color = Color.MAGENTA;
	}

	// Drawbehavior is a strategy object for drawing
	public DrawObject(Drawable drawBehavior, Color color, Point2D position, Point2D dimensions) {
		super();
		this.drawBehaviour = drawBehavior;
		this.position = position;
		this.dimensions = dimensions;
		this.color = color;
	}

	public DrawObject(Drawable drawBehavior, Color color, Point2D position, Point2D dimensions, Image image) {
		super();
		this.drawBehaviour = drawBehavior;
		this.position = position;
		this.dimensions = dimensions;
		this.color = color;
		this.image = image;
	}

	public Point2D getPosition() {
		return position;
	}

	public void setPosition(float x, float y) {
		position = new Point2D(x, y);
	}

	public void setPosition(Point2D position) {
		this.position = position;
	}

	public Point2D getDimensions() {
		return dimensions;
	}

	public void setDimensions(int width, int height) {
		dimensions = new Point2D(width, height);
	}

	public void setDimensions(Point2D dimensions) {
		this.dimensions = dimensions;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

//	public CommandListener getCommandListener() {
//		return commandListener;
//	}
//	
//	public void setCommandListener(CommandListener listener) {
//		commandListener = listener;
//	}

	public Drawable getDrawBehaviour() {
		return drawBehaviour;
	}

	public Image getImage() {
		return image;
	}
}
