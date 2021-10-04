package collisionBehaviors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import game.engine.GameObject;

public class FallDownTest {
	
	@Test
	void checkName() {
		FallDown fd = new FallDown(new GameObject());
		assertEquals("Fall Down",fd.getName());
	}

}
