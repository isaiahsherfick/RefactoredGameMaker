package sprite;

import org.json.simple.JSONObject;

import constants.Constants;
import game.engine.Drawable;
import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import saveandload.Saveable;
import saveandload.SaveableImage;
import saveandload.SaveableShape;

//Author Isaiah Sherfick
//Will be responsible for representing sprites visually
//as either a shape or an image
//Last edited by Isaiah Sherfick
public class Appearance implements Drawable, Saveable
{
	private Point2D location;
	private Point2D size;
	private SaveableImage image;
	private SaveableShape shape;
	private int shapeOrImage;
	
	public Appearance()
	{
		location = new Point2D(Constants.DEFAULT_SPRITE_X, Constants.DEFAULT_SPRITE_Y);
		size = new Point2D(Constants.DEFAULT_SPRITE_WIDTH, Constants.DEFAULT_SPRITE_HEIGHT);
		
		image = new SaveableImage();
		

		//Rectangle by default
		shape = new SaveableShape();
		
		//shape by default
		shapeOrImage = Constants.SHAPE;
	}
	
	public void setImage()
	{
		shapeOrImage = Constants.IMAGE;
	}
	
	public void setShape()
	{
		shapeOrImage = Constants.SHAPE;
	}

	public void setX(double x)
	{
		double curX = location.getX();
		double y = location.getY();
		x += curX;
		location = new Point2D(x,y);
	}

	public void setY(double y)
	{
		double curY = location.getY();
		double x = location.getX();
		y += curY;
		location = new Point2D(x,y);
	}

	public void setWidth(double width)
	{
		double curWidth = location.getX();
		double height = location.getY();
		width += curWidth;
		location = new Point2D(width,height);
	}

	public void setHeight(double height)
	{
		double curHeight = location.getY();
		double width = location.getX();
		height += curHeight;
		location = new Point2D(width,height);
	}
	
	public Point2D getSize()
	{
		return size;
	}

	public Point2D getLocation() 
	{
		return location;
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject save() 
	{
		double x = location.getX();
		double y = location.getY();
		double width = size.getX();
		double height = size.getY();
		JSONObject saveObj = new JSONObject();
		saveObj.put("type","HitBox");
		saveObj.put("x",x);
		saveObj.put("y",y);
		saveObj.put("width",width);
		saveObj.put("height",height);
		
		if (shapeOrImage == Constants.SHAPE)
		{
			//Save the shape
		}
		else 
		{
			//Save the image
		}
		
		return saveObj;
	}

	@Override
	public void load(JSONObject saveJSON) 
	{
		double x = (double)saveJSON.get("x");
		double y = (double)saveJSON.get("y");
		double width = (double)saveJSON.get("width");
		double height = (double)saveJSON.get("height");
		
		location = new Point2D(x,y);
		size = new Point2D(width,height);
	}

	@Override
	public void draw(GraphicsContext g) 
	{
		// TODO Auto-generated method stub
		
	}

	//DEPRECATED -- remove after interface changes
	@Override
	public void draw(Sprite sprite, FlowPane gameFlow) 
	{
		// TODO Auto-generated method stub
		
	}
	

}
