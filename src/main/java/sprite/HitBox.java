package sprite;

import org.json.simple.JSONObject;

import constants.Constants;
import javafx.geometry.Point2D;
import saveandload.Saveable;

public class HitBox implements Saveable
{
	//Top left x,y value
	private Point2D location;
	
	//width,height
	private Point2D size;
	
	public HitBox()
	{
		location = new Point2D(Constants.DEFAULT_SPRITE_X,Constants.DEFAULT_SPRITE_Y);
		size = new Point2D(Constants.DEFAULT_SPRITE_WIDTH, Constants.DEFAULT_SPRITE_HEIGHT);
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
		return saveObj;
	}

	@Override
	public void load(JSONObject saveJSON) 
	{
		//Fairly certain that these will be interpreted as longs when we load from a file
		double x = (double)saveJSON.get("x");
		double y = (double)saveJSON.get("y");
		double width = (double)saveJSON.get("width");
		double height = (double)saveJSON.get("height");
		
		location = new Point2D(x,y);
		size = new Point2D(width,height);
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
	
	public boolean equals(Object o)
	{
		if (o instanceof HitBox)
		{
			HitBox other = (HitBox)o;
			return size.equals(other.getSize()) && location.equals(other.getLocation());
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

	public Point2D getTopRight() 
	{
		return new Point2D(getX() + getWidth(), getY());
	}
	public Point2D getTopLeft() 
	{
		return location;
	}
	public Point2D getBottomLeft() 
	{
		return new Point2D(getX(), getY() + getHeight());
	}
	private double getHeight() 
	{
		return size.getY();
	}

	public Point2D getBottomRight() 
	{
		return new Point2D(getX() + getWidth(), getY() + getHeight());
	}

	private double getWidth() 
	{
		return size.getX();
	}
}
