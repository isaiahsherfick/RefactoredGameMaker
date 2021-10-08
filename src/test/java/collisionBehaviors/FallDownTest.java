package collisionBehaviors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import game.engine.Sprite;

public class FallDownTest {
	
	@Test
    //TODO
    //Actually test this stuff
    //-Isaiah
	void checkName() {
		FallDown fd = new FallDown(new Sprite());
		assertEquals("Fall Down",fd.getName());
	}

}
