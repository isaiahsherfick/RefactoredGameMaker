package sound;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FireSoundTest {
	
	@Test
	void getNameTest() {
		FireSound fTest = new FireSound();
		assertEquals("Fire",fTest.getName());
	}
}
