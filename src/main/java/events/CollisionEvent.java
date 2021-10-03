package events;

import strategies.Strategy;

public class CollisionEvent extends Event {

	public CollisionEvent(Strategy action) {
		super.setAction(action);
	}
}
