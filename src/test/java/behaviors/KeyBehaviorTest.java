package behaviors;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class KeyBehaviorTest {
	
	@Test
    //TODO
    //not much of a test
    //-Isaiah
	void checkName() {
		KeyBehavior keyBehavior = new KeyBehavior();
		assertEquals("Key Behaviour",keyBehavior.getName());
	}
}
