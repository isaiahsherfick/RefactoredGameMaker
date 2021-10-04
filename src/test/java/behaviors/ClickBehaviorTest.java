package behaviors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ClickBehaviorTest {
	
	@Test
	void checkName() {
		ClickBehavior clickBehavior = new ClickBehavior();
		assertEquals("Click Behaviour",clickBehavior.getName());
	}
}
