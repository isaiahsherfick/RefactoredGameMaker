package behaviors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TimedBehaviorTest {
	
	@Test
    //TODO
    //not much of a test
    //-Isaiah
	void checkName() {
		TimedBehavior timedBehavior = new TimedBehavior();
		assertEquals("Time Behavior",timedBehavior.getName());
	}
}
