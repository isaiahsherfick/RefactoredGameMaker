package saveandload;

import org.json.simple.JSONObject;

import constants.Constants;
import game.engine.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sprite.Sprite;

public class SaveableRectangle implements SaveableShape, Drawable
{
	private Rectangle rect;
	private double x,y,width,height;
	private Color color;
	
	public SaveableRectangle()
	{
		x = Constants.DEFAULT_SPRITE_X;
		y = Constants.DEFAULT_SPRITE_Y;
		width = Constants.DEFAULT_SPRITE_WIDTH;
		height = Constants.DEFAULT_SPRITE_HEIGHT;
		rect = new Rectangle();
		color = Color.web("#FFFFFF");
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type","SaveableRectangle");
		json.put("x",x);
		json.put("y",y);
		json.put("width",width);
		json.put("height",height);
		json.put("color",color.toString());
		return json;
	}

	@Override
	public void load(JSONObject saveJSON) 
	{
		x = (double)saveJSON.get("x");
		y = (double)saveJSON.get("y");
		width = (double)saveJSON.get("width");
		height = (double)saveJSON.get("height");
		rect = new Rectangle(x,y,width,height);
		color = Color.web((String)saveJSON.get("color"));
	}

	@Override
	public Color getColor() 
	{
		return color;
	}

	@Override
	public void draw(GraphicsContext g) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getWidth() 
	{
		return width;
	}

	@Override
	public double getHeight() 
	{
		return height;
	}

	@Override
	public void setWidth(double w) 
	{
		width = w;
		rect.setWidth(width);
	}

	@Override
	public void setHeight(double h) 
	{
		height = h;
		rect.setHeight(h);
	}


	@Override
	public double getX() 
	{
		return x;
	}

	@Override
	public double getY() 
	{
		return y;
	}

	@Override
	public void setX(double newX) 
	{
		x = newX;
		rect.setX(x);
	}

	@Override
	public void setY(double newY) 
	{
		y = newY;
		rect.setX(y);
	}

	//Deprecated, remove after drawable interface is changed
	@Override
	public void draw(Sprite sprite, FlowPane gameFlow) 
	{
		// TODO Auto-generated method stub
		
	}
	
	public boolean equals(Object o)
	{
		if (o instanceof SaveableRectangle)
		{
			SaveableRectangle r = (SaveableRectangle)o;
			return x == r.getX() && y == r.getY() && width == r.getWidth() && height == r.getHeight();
		}
		return false;
	}
	
	
}
