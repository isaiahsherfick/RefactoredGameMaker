package behaviors;

import saveandload.Saveable;

public interface EventBehavior extends Saveable
{
	public void onMousePress();
	public void onGameStart();
	public void onMouseMove();
	public void onKeyPress();
	public void onGameTick();
	public void onLevelIncrease();
}
