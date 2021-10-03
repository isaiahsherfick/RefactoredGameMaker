package sound;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class NoSoundTest {
	
	@Test
	void getNameTest() {
		NoSound nTest = new NoSound();
		assertEquals(null, nTest.getName());
	}

	

}
