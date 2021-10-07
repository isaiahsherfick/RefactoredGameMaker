package events;

import strategies.Strategy;

//TODO
//These are not events...
//-Isaiah
//This isnt ever used anywhere, since Events and Actions got merged because of terribly communicated architecture decisions - Christian

public class CollisionEvent extends Event {

	public CollisionEvent(Strategy action) {
		super.setAction(action);
	}
}
