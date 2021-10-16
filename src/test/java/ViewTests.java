import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.Model;
import views.View;

class ViewTests 
{

	@Test
	void createSpriteTest() 
	{
		View v = new View();
		Controller c = new Controller();
		Model m = new Model();
		v.setController(c);
		c.setView(v);
		c.setModel(m);
		m.registerObserver(v);
		
		assertEquals(0, m.getNumberOfSprites());
	    v.createSpriteButtonClicked(new ActionEvent());
	    assertEquals(1, m.getNumberOfSprites());
	    v.undoPauseButtonClicked(new ActionEvent());
	    assertEquals(0, m.getNumberOfSprites());
	}
}
