package sound;

//TODO add a generalized implementation of this class rather than each sound being its own class. Makes everything cleaner.
public interface Sound {
	
	 void playSound();
	
	 void stopSound();

	String getName();
	
}
