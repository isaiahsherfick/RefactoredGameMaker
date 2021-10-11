package command;

import sprite.Sprite;
import sprite.SpriteManager;

/**
 * @author ramya
 * The following class is dedicated to  create/remove sprite objects commands 
 * 
 */

public class CreateSpriteCommand implements Command {
	
	private SpriteManager spritemanager;
	private Sprite sprite;
	private int spriteNumber;
	
	
	// Constructor
	// Receives the spritemanager reference and sprite object references to be able to add and remove the sprite from the sprite manager 
	public CreateSpriteCommand(SpriteManager spritemanager,Sprite sprite, int spriteNumber)
	{
		this.sprite=sprite;
		this.spritemanager=spritemanager;
		this.spriteNumber=spriteNumber;
	}
	
	@Override
	public void execute() {
		spritemanager.add(sprite);

	}

	@Override
	public void unexecute() {
		spritemanager.remove(spriteNumber);

	}

}