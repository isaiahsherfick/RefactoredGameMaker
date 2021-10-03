package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import game.engine.GameObject;
import javafx.scene.image.ImageView;

public class MakerControllerTest {
	
	@Test
	void currentlySelectedObjectTest() {
		MakerController makerController = new MakerController();
		makerController.addNewGameObject("Rectangle", new ImageView(), "Paddle", "RED");
		GameObject testGameObject = makerController.getCurrentlySelectedObject();
		assertEquals("Paddle", testGameObject.getObjectName());
	}
	

}