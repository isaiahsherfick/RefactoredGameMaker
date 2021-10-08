package saveandload;

import org.json.simple.JSONObject;

import game.engine.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sprite.Sprite;

public class SaveableRectangle implements SaveableShape, Drawable
{
	private Rectangle rect;
	private Color color;
	
	public SaveableRectangle()
	{
		rect = new Rectangle();
		color = Color.web("#FFFFFF");
	}

	@Override
	public JSONObject save() 
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void load(JSONObject saveJSON) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Color getFillColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void draw(GraphicsContext g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setWidth() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setHeight() {
		// TODO Auto-generated method stub
		
	}

	//Deprecated, remove after drawable interface is changed
	@Override
	public void draw(Sprite sprite, FlowPane gameFlow) {
		// TODO Auto-generated method stub
		
	}
	
	
}
