package behaviors.collision;

import java.util.ArrayList;

import org.json.simple.JSONObject;

import constants.Constants;
import sprite.Sprite;

public class PacManRandomCollisionBehaviour implements CollisionBehavior 
{

	@Override
	public JSONObject save() 
	{
		JSONObject json = new JSONObject();
		json.put("type","PacManRandomCollisionBehaviour");
		return json;
	}

	@Override
	public void load(JSONObject saveJSON) 
	{

	}

	@Override
	public void collide(Sprite collidee, int colliderId) 
	{
		int xVelocity=collidee.getXVelocity();
		int yVelocity=collidee.getYVelocity();
		int xSpeed = Math.abs(xVelocity);
		int ySpeed = Math.abs(yVelocity);
		int speed = Math.max(xSpeed, ySpeed);
		ArrayList<Integer> possibleDirections = new ArrayList<Integer>();
		
	
		if(xVelocity==0 && yVelocity>0)// sprite moving down 
		{
			System.out.println("Sprite going down");
			collidee.setY(collidee.getY() - Constants.SPRITE_COLLISION_WARP_DISTANCE);
			possibleDirections.add(Constants.DIRECTION_UP);
			possibleDirections.add(Constants.DIRECTION_RIGHT);
			possibleDirections.add(Constants.DIRECTION_LEFT);
		}
		else if(xVelocity==0 && yVelocity<0)// sprite moving up 
		{
			System.out.println("Sprite going up");
			collidee.setY(collidee.getY() + Constants.SPRITE_COLLISION_WARP_DISTANCE);
			possibleDirections.add(Constants.DIRECTION_DOWN);
			possibleDirections.add(Constants.DIRECTION_RIGHT);
			possibleDirections.add(Constants.DIRECTION_LEFT);
		} 
		else if(yVelocity==0 && xVelocity>0)// sprite moving right
		{
			System.out.println("Sprite going right");
			collidee.setX(collidee.getX() - Constants.SPRITE_COLLISION_WARP_DISTANCE);
			possibleDirections.add(Constants.DIRECTION_UP);
			possibleDirections.add(Constants.DIRECTION_DOWN);
			possibleDirections.add(Constants.DIRECTION_LEFT);
		}
		else if((yVelocity==0 && xVelocity<0))// sprite moving left
		{
			System.out.println("Sprite going left");
			collidee.setX(collidee.getX() + Constants.SPRITE_COLLISION_WARP_DISTANCE);
			possibleDirections.add(Constants.DIRECTION_UP);
			possibleDirections.add(Constants.DIRECTION_RIGHT);
			possibleDirections.add(Constants.DIRECTION_DOWN);
		}
		
		int guessIndex = Constants.RANDOM.nextInt(3);
		int guess = possibleDirections.get(guessIndex);
		System.out.println("guess: " + guess);
		
		switch(guess)
		{
			case Constants.DIRECTION_UP:
				xVelocity = 0;
				yVelocity = -1*speed;
				break;
			case Constants.DIRECTION_RIGHT:
				xVelocity = speed;
				yVelocity = 0;
				break;
			case Constants.DIRECTION_LEFT:
				xVelocity = -1 * speed;
				yVelocity = 0;
				break;
			case Constants.DIRECTION_DOWN:
				xVelocity = 0;
				yVelocity = 1 * speed;
				break;
		}
		
		collidee.setXVelocity(xVelocity);
		collidee.setYVelocity(yVelocity);
	}
	
	public boolean equals(Object o)
	{
		return o instanceof PacManRandomCollisionBehaviour;
	}

	/**
	 * The following method tries all possible direction for the sprite object to move when it impacts another sprite 
	 * Ex: Ghost sprite in pac man must change course whenever it comes across a wall
	 */
	public void changeDirection() 
	{
			
	}
		
	
	public String toString() {
		return "Guess New Direction";
	}
}
