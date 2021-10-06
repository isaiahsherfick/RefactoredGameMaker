package events;

import strategies.Strategy;

//TODO
//These are not events...
//-Isaiah

public class CollisionEvent extends Event {

	public CollisionEvent(Strategy action) {
		super.setAction(action);
	}
}
