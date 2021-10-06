package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SoundButtonControllerTest {
	
	@Test
    //TODO
    //Surely there are other things to test here..
    //-Isaiah
	void addSoundTest() {
		SoundButtonController sbcTest = new SoundButtonController();
		assertEquals(2,sbcTest.getAllSounds().size());
	}

}
