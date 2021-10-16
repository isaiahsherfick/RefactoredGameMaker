package controller;

import java.util.ArrayList;
import java.util.Set;

import pattern.Observer;
import sprite.HitBox;
import sprite.Sprite;
import sprite.SpriteManager;

//CollisionManager will observe the game clock
public class CollisionManager implements Observer
{
	private ArrayList<Sprite> sprites;
	public CollisionManager()
	{
		sprites = new ArrayList<>();
	}
	
	public void handleAllCollisions(SpriteManager spriteManager)
	{
		Set<Integer> spriteIds = spriteManager.getSpriteIds();
		for (Integer spriteId : spriteIds)
		{
			Sprite currentSprite = spriteManager.get(spriteId);
			HitBox currentHitBox = currentSprite.getHitBox();
		}
	}

	@Override
	public void update() 
	{
		handleAllCollisions();
	}
}
