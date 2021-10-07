package sound;

import java.io.File;

import controller.SoundButtonController;
import javafx.scene.media.AudioClip;

//TODO
//I feel that having specific sound classes is unnecessary
//we can just use one Sound class and the user can pick an explosion if that's what they want
//How this runs sounds is fine, just can be more generalized to run any sound.
public class Explosion implements Sound 
{
	
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
}
