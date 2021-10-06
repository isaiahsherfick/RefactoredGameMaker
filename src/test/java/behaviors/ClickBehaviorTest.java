package behaviors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ClickBehaviorTest {
	
	@Test
    //TODO
    //not much of a test really...
    //-Isaiah
	void checkName() {
		ClickBehavior clickBehavior = new ClickBehavior();
		assertEquals("Click Behaviour",clickBehavior.getName());
	}
}
