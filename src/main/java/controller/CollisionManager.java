package controller;

import java.util.ArrayList;

import pattern.Observer;
import sprite.Sprite;

//CollisionManager will observe the game clock
public class CollisionManager implements Observer
{
	private ArrayList<Sprite> sprites;
	public CollisionManager()
	{
		sprites = new ArrayList<>();
	}
	
	public void handleAllCollisions()
	{
		
	}

	@Override
	public void update(double timeDelta) {
		// TODO Auto-generated method stub
		
	}
}
