package saveandload;

import org.json.simple.JSONObject;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface SaveableShape extends Saveable
{
	public Color getFillColor();
	public void draw(GraphicsContext g);
	public int getWidth();
	public int getHeight();
	public void setWidth();
	public void setHeight();
}
