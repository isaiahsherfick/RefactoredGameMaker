package sprite;

import constants.Constants;

public class NullSprite extends Sprite
{
	public NullSprite()
	{
		super();
		spriteId = Constants.NULL_SPRITE_ID;
	}
	
	public Sprite copy()
	{
		return new NullSprite();
	}
}
