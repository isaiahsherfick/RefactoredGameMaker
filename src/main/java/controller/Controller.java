package controller;

import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.parser.ParseException;

import command.Command;
import command.CommandInvoker;
import command.CreateSpriteCommand;
import command.ModifySpriteCommand;
import constants.Constants;
import model.Model;
import sprite.Sprite;
import views.View;
/**
 * @author ramya
 * The following class is responsible for creating commands and 
 * passing them to the model for every event which can occur in the view
 */



public class Controller 
{
	private Model model;
	private View view;
	private GameClock gameClock;
	 
	 //constructor
	 public Controller(Model m, View v)
	 {
			model = m;
			view = v;
			gameClock = new GameClock();
	 }  
	 
	 public Controller() 
	 {
		// TODO Auto-generated constructor stub
	 } 
	 

	// It creates the "create sprite" command and passes it to the commandInvoker 
	 public void createSprite()
	 {
		 Command createSprite=new CreateSpriteCommand(model);
		 model.receiveCommand(createSprite);
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
		 //tell the view to switch buttons to the play buttons
		 //turn on the game clock
		 //enable keylisteners/mouselisteners for game engine
	 }
	 
	 public void pause()
	 {
		 //pause the game clock
	 }
	 
	 public void resume()
	 {
		 //unpause the game clock
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
	    	   

}
