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
import saveandload.SaveableRectangle;
import saveandload.SaveableShape;
import saveandload.ShapeLoader;

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
		shape = new SaveableRectangle();
		
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
		double y = location.getY();
		location = new Point2D(x,y);
		shape.setX(x);
		image.setX(x);
	}

	public void setY(double y)
	{
		double x = location.getX();
		location = new Point2D(x,y);
		shape.setY(y);
		image.setY(y);
	}

	public void setWidth(double width)
	{
		double height = size.getY();
		size = new Point2D(width,height);
		shape.setWidth(width);
		image.setWidth(width);
	}

	public void setHeight(double height)
	{
		double width = size.getX();
		size = new Point2D(width,height);
		shape.setHeight(height);
		image.setHeight(height);
	}

	private SaveableShape getShape() 
	{
		return shape;
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
		saveObj.put("shape",shape.save());
		//saveObj.put("image",image.save()); TODO
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
		shape = ShapeLoader.loadShape((JSONObject)saveJSON.get("shape"));
//		image = new Image();
//		image.load((JSONObject)saveJSON.get("image"));
	}

	@Override
	//This is the one we will actually use
	public void draw(GraphicsContext g) 
	{
		if (shapeOrImage == Constants.SHAPE)
		{
			
		}
		else
		{
			
		}
	}
	
	@Override
	public boolean equals(Object o)
	{
		if (o instanceof Appearance)
		{
			Appearance a = (Appearance) o;
			return location.equals(a.getLocation()) && size.equals(a.getSize()) && shape.equals(a.getShape()); //&&image.equals(a.getImage()); //TODO
		}
		return false;
	}
	
	public double getX()
	{
		return location.getX();
	}
	public double getY()
	{
		return location.getY();
	}
	public double getWidth()
	{
		return size.getX();
	}
	public double getHeight()
	{
		return size.getY();
	}

	//DEPRECATED -- remove after interface changes
	@Override
	public void draw(Sprite sprite, FlowPane gameFlow) 
	{
		// TODO Auto-generated method stub
		
	}
	
	public String toString()
	{
		return String.format("Appearance: [x=%s,y=%s,width=%s,height=%s]",getX(),getY(),getWidth(),getHeight());
	}
}
