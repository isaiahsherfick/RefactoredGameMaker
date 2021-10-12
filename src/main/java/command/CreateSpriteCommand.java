package command;


import main.GameMaker;
import model.Model;
import sprite.Sprite;
import sprite.SpriteManager;

/**
 * @author ramya
 * The following class is dedicated to  create/remove sprite objects commands 
 * 
 */

public class CreateSpriteCommand implements Command {

	private Sprite sprite;
	private Model model;

	
	//constructor 1 : Empty Constructor 
	public CreateSpriteCommand()
	{
		model=GameMaker.getModel();
	}
	
	@Override
	public void execute() {
		sprite=model.createSprite();
		model.addSprite(sprite);

	}

	@Override
	public void unexecute() {
		model.removeSprite(sprite);

	}

}