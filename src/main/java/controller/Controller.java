package controller;

import java.io.IOException;

import command.Command;
import command.CommandInvoker;
import command.CreateSpriteCommand;
import command.ModifySpriteCommand;
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
	 
	 //constructor
	 public Controller(Model m, View v)
	 {
			model = m;
			view = v;
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
	    	   

}
