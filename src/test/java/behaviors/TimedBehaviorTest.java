package behaviors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TimedBehaviorTest {
	
	@Test
	void checkName() {
		TimedBehavior timedBehavior = new TimedBehavior();
		assertEquals("Time Behavior",timedBehavior.getName());
	}
}
