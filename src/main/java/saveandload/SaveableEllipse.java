package saveandload;

import org.json.simple.JSONObject;

import constants.Constants;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import sprite.Drawable;
import sprite.Sprite;

import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class SaveableEllipse implements SaveableShape, Drawable
{
	private Ellipse ellipse;
	private double x,y,width,height;
	private Color color;
	
	public SaveableEllipse()
	{
		x = Constants.DEFAULT_SPRITE_X;
		y = Constants.DEFAULT_SPRITE_Y;
		width = Constants.DEFAULT_SPRITE_WIDTH;
		height = Constants.DEFAULT_SPRITE_HEIGHT;
		ellipse = new Ellipse(x,y,width,height);
		color = Color.web("#FF0000");
	}

	@SuppressWarnings("unchecked")
	@Override
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type","SaveableEllipse");
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
		ellipse = new Ellipse(x,y,width,height);
		color = Color.web((String)saveJSON.get("color"));
	}

	@Override
	public Color getColor() 
	{
		return color;
	}
	
	public void setColor(Color c) {
		color = c;
	}
	

	@Override
	//Ellipses will have a weird draw method, since they are the exception to the rule of drawing from the top left corner in jfx
	//to offset this for the user experience, we will draw the ellipse at x + .5w, y+ .5h
	public void draw(GraphicsContext g) 
	{
		Graphics2D g2 = (Graphics2D)g;
		g2.setFill(color);
		g2.fill(new Ellipse2D.Double(x,y,width,height));
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
		ellipse.setRadiusX(width);
	}

	@Override
	public void setHeight(double h) 
	{
		height = h;
		ellipse.setRadiusY(height);
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
		ellipse.setCenterX(x);
	}

	@Override
	public void setY(double newY) 
	{
		y = newY;
		ellipse.setCenterY(y);
	}

	public boolean equals(Object o)
	{
		if (o instanceof SaveableEllipse)
		{
			SaveableEllipse r = (SaveableEllipse)o;
			return x == r.getX() && y == r.getY() && width == r.getWidth() && height == r.getHeight();
		}
		return false;
	}

	
}
