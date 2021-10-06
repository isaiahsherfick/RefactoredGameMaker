package behaviors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CollisionBehaviorTest {
	
	@Test
    //TODO
    //not much of a test
    //-Isaiah
	void checkName() {
		CollisionBehavior collisionBehavior = new CollisionBehavior();
		assertEquals("Collision Behaviour",collisionBehavior.getName());
	}
}
