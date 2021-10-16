package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

import org.json.simple.parser.ParseException;

import command.Command;
import command.CreateSpriteCommand;
import command.DuplicateSpriteCommand;
import command.ModifySpriteCommand;
import constants.Constants;
import model.Model;
import pattern.Observer;
import sprite.Sprite;
import sprite.SpriteManager;
import views.View;
/**
 * @author ramya
 * The following class is responsible for creating commands and 
 * passing them to the model for every event which can occur in the view
 */



//Controller will observe the gameclock
public class Controller implements Observer
{
	private Model model;
	private View view;
	private GameClock gameClock;
	private Timer timer;
	private CollisionManager collisionManager;
	 
	 //constructor
	 public Controller(Model m, View v)
	 {
			model = m;
			view = v;
			collisionManager = new CollisionManager();
			gameClock = new GameClock();
			gameClock.register(this);
			timer = new Timer();
	 }  
	 
	 public Controller() 
	 {
			collisionManager = new CollisionManager();
			gameClock = new GameClock();
			gameClock.register(this);
			timer = new Timer();
	 } 
	 

	// It creates the "create sprite" command and passes it to the commandInvoker 
	 public void createSprite()
	 {
		 Command createSprite=new CreateSpriteCommand(model);
		 model.receiveCommand(createSprite);
	 }
	 
	 //Creates the duplicate sprite command and passes to commandInvoker
	 public void duplicateSprite(Sprite s) {
		 Command duplicateSprite = new DuplicateSpriteCommand(model, s);
		 model.receiveCommand(duplicateSprite);
	 }
	 
	 //returns true on success
	 //false on IOException (bad filepath)
	 public boolean save()
	 {
		 try 
		 {
			model.save();
		 } 
		 catch (IOException e) 
		 {
			 return false;
		 }
		 return true;
	 }
	 
	 public ArrayList<Sprite> getSpriteList()
	 {
		 return model.getSpriteList();
	 }
	 
	 public void play()
	 {
		 gameClock = new GameClock();
		 gameClock.register(this);
		 timer = new Timer();
		 timer.schedule(gameClock, (long)0.0, (long)gameClock.getMsBetweenTicks());
	 }
	 
	 public void pause()
	 {
		 timer.cancel();
	 }
	 
	 public void resume()
	 {
		 gameClock = new GameClock();
		 gameClock.register(this);
		 timer = new Timer();
		 timer.schedule(gameClock, (long)0.0, (long)gameClock.getMsBetweenTicks());
	 }
	 
	 public void modifySprite(Sprite modifiedSprite)
	 {
		 ModifySpriteCommand modifyCommand = new ModifySpriteCommand(model, modifiedSprite);
		 model.receiveCommand(modifyCommand);
	 }
	 
	 public void undo()
	 {
		 model.undo();
	 }

	public void setModel(Model m) 
	{
		model = m;
	}
	

	 public void setView(View view) {
		 this.view = view;
	 }

	public Sprite getSprite(int spriteId) 
	{
		return model.getSprite(spriteId);
	}

	//Returns constants depending on success
	// 0 for success
	// 1 for IOError (bad filepath)
	// 2 for ParseError (corrupted JSON)
	public int load() 
	{
		try
		{
			model.load();
			return Constants.LOAD_SUCCESS;
		}
		catch(IOException e)
		{
			return Constants.LOAD_BADFILE;
		}
		catch(ParseException e)
		{
			return Constants.LOAD_BADJSON;
		}

	}

	public GameClock getClock() 
	{
		return gameClock;
	}

	@Override
	public void update() 
	{
		SpriteManager sm = model.getSpriteManager();
		sm.onGameTick();
		collisionManager.handleAllCollisions(sm);
		model.notifyObservers();
	}	
	    	   

}
