package controller;

import java.util.ArrayList;
import java.util.Set;

import javafx.geometry.Point2D;
import sprite.HitBox;
import sprite.Sprite;
import sprite.SpriteManager;

public class CollisionManager
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
			for (Integer spriteId2 : spriteIds)
			{
				if (!(spriteId == spriteId2))
				{
					Sprite spriteToCheck = spriteManager.get(spriteId2);
					HitBox toCheckHitBox = spriteToCheck.getHitBox();
					
					Point2D currentTopLeft = currentHitBox.getTopLeft();
					Point2D currentTopRight = currentHitBox.getTopRight();
					Point2D currentBottomLeft = currentHitBox.getBottomLeft();
					Point2D currentBottomRight = currentHitBox.getBottomRight();
					
					Point2D toCheckTopLeft = toCheckHitBox.getTopLeft();
					Point2D toCheckTopRight = toCheckHitBox.getTopRight();
					Point2D toCheckBottomLeft = toCheckHitBox.getBottomLeft();
					Point2D toCheckBottomRight = toCheckHitBox.getBottomRight();

					
				}
			}
		}
	}
}
