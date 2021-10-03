package sound;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


public class ExplosionTest {
	
	@Test
	void getNameTest() {
		Explosion eTest = new Explosion();
		assertEquals("Explosion",eTest.getName());
	}
}
