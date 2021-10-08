package collisionBehaviors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import behaviors.ClickBehavior;
import game.engine.Sprite;

public class BlowUpTest {
	
	@Test
    //TODO
    //This is seriously the only thing that got tested for all of the behaviors?
    //-Isaiah
	void checkName() {
		BlowUp blowUp = new BlowUp(new Sprite());
		assertEquals("Blow Up",blowUp.getName());
	}
}
