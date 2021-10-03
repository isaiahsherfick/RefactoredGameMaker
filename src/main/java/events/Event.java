package events;

import strategies.Strategy;

public abstract class Event {

	protected Strategy action;

	public Strategy getAction() {
		return action;
	}

	public void setAction(Strategy action) {
		this.action = action;
	}
}
