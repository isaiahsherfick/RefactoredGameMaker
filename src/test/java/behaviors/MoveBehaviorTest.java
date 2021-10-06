package behaviors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class MoveBehaviorTest {
	
	@Test
    //TODO
    //not much of a test
    //-Isaiah
	void checkName() {
		MoveBehavior moveBehavior = new MoveBehavior();
		assertEquals("Move Behaviour",moveBehavior.getName());
	}
}
