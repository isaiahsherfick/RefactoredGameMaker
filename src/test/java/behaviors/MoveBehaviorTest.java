package behaviors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MoveBehaviorTest {
	
	@Test
	void checkName() {
		MoveBehavior moveBehavior = new MoveBehavior();
		assertEquals("Move Behaviour",moveBehavior.getName());
	}
}
