package sound;

import java.io.File;

import controller.SoundButtonController;
import javafx.scene.media.AudioClip;

public class Explosion implements Sound {
	
	private static final ClassLoader CLASS_LOADER = Explosion.class.getClassLoader();
	private File MUSIC_FILE = new File(CLASS_LOADER.getResource("short-explosion.wav").getFile());
	private String name = "Explosion";

	public void playSound() {
		
		  AudioClip explosionSound = new AudioClip(MUSIC_FILE.toURI().toString());
	      explosionSound.play();
	}

	public void stopSound() {
		
	}
	
	public String getName() {
		return this.name;
	}

//	@Override
//	public void attachSound() {
//		// TODO Auto-generated method stub
//		soundBtnController.addSound(this);
//		
//	}

}
