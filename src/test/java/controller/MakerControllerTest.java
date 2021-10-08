package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import game.engine.Sprite;
import javafx.scene.image.ImageView;

public class MakerControllerTest {
	
	@Test

    //TODO
    //This is one trivial test case, needs a lot more
    //-Isaiah
	void currentlySelectedObjectTest() {
		MakerController makerController = new MakerController();
		makerController.addNewGameObject("Rectangle", new ImageView(), "Paddle", "RED");
		Sprite testGameObject = makerController.getCurrentlySelectedObject();
		assertEquals("Paddle", testGameObject.getObjectName());
	}
	

}
