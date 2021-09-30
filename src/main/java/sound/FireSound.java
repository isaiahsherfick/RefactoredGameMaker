package sound;

import java.io.File;

import javafx.scene.media.AudioClip;

public class FireSound implements Sound {

	private static final ClassLoader CLASS_LOADER = Explosion.class.getClassLoader();
	private File MUSIC_FILE = new File(CLASS_LOADER.getResource("fire.wav").getFile());

	public void playSound() {

		AudioClip explosionSound = new AudioClip(MUSIC_FILE.toURI().toString());
		explosionSound.play();
	}

	public void stopSound() {

	}
}
