package sound;

import java.io.File;

import controller.SoundButtonController;
import javafx.scene.media.AudioClip;

public class Explosion implements Sound {
	
	private static final ClassLoader CLASS_LOADER = Explosion.class.getClassLoader();
	private File MUSIC_FILE = new File(CLASS_LOADER.getResource("short-explosion.wav").getFile());
	private String name = "Explosion";
	private AudioClip explosionSound = new AudioClip(MUSIC_FILE.toURI().toString());
	//private AudioClip explosionSound = null;

	public void playSound() {
		try {
			if(!explosionSound.isPlaying()) {
		      explosionSound.play();
			}
		}
		catch(Exception ex) {
			
		}
	}

	public void stopSound() {
		try {
			explosionSound.stop();
		}
		catch(Exception ex) {
			
		}
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
