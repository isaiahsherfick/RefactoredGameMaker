package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import javafx.scene.image.ImageView;
import sprite.Sprite;

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
