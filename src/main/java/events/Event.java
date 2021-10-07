package events;

import strategies.Strategy;

//TODO
//These are not events...
//-Isaiah
//Also not used anywhere, can likely be removed - Christian

public abstract class Event {

	protected Strategy action;

	public Strategy getAction() {
		return action;
	}

	public void setAction(Strategy action) {
		this.action = action;
	}
}
