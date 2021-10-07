package sound;

public class NoSound implements Sound{

	public void playSound() {
		//Null Object class, does nothing
	}

	public void stopSound() {
		//Null Object class, does nothing
	}

	//TODO this should probably return an empty string instead of null
	//ironic npe danger from a nullobject
	//isaiah
	public String getName() {
		return null;
	}

}
