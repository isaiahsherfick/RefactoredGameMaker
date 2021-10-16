package controller;

import java.util.ArrayList;
import java.util.Set;

import javafx.geometry.Point2D;
import sprite.HitBox;
import sprite.Sprite;
import sprite.SpriteManager;
import java.awt.Rectangle;
import java.awt.Shape;

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
			
			//This is O(n2), could be O(n) if we want to do some clever hash map stuff but that's not important right now
			for (Integer spriteId2 : spriteIds)
			{
				if (!(spriteId == spriteId2))
				{
					Sprite spriteToCheck = spriteManager.get(spriteId2);
					HitBox toCheckHitBox = spriteToCheck.getHitBox();
					
					Point2D currentTopRight = currentHitBox.getTopRight();
					Point2D currentBottomLeft = currentHitBox.getBottomLeft();
					int currentX = (int)currentBottomLeft.getX();
					int currentY = (int)currentTopRight.getY();
					int currentW = (int)(currentTopRight.getX() - currentBottomLeft.getX());
					int currentH = (int)(currentBottomLeft.getY() - currentTopRight.getY());
					
					Point2D toCheckTopRight = toCheckHitBox.getTopRight();
					Point2D toCheckBottomLeft = toCheckHitBox.getBottomLeft();
					int toCheckX = (int)toCheckBottomLeft.getX();
					int toCheckY = (int)toCheckTopRight.getY();
					int toCheckW = (int)(toCheckTopRight.getX() - toCheckBottomLeft.getX());
					int toCheckH = (int)(toCheckBottomLeft.getY() - toCheckTopRight.getY());
					
					Rectangle currentRect = new Rectangle(currentX, currentY, currentW, currentH);
					Rectangle toCheckRect = new Rectangle(toCheckX, toCheckY, toCheckW, toCheckH);
					
					if (currentRect.intersects(toCheckRect))
					{
						currentSprite.collide(spriteToCheck.getSpriteId());
					}
				}
			}
		}
	}
}
