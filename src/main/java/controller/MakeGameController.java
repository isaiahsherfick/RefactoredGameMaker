package controller;

import command.Command;
import command.CommandInvoker;
import command.CreateSpriteCommand;
import model.Model;
/**
 * @author ramya
 * The following class is responsible for making call to the Command Invoker,
 * for every event that happens in the Maker View(screen/window)
 *
 */

public class MakeGameController {
	
	 private  CommandInvoker invoker ;

	 
	 //constructor
	 public MakeGameController()
	 {
	     invoker=new CommandInvoker();  
			
	 }  
	 
	 // It creates the "create sprite" command and passes it to the commandInvoker 
	 public void createSprite()
	 {
		 Command createSprite=new CreateSpriteCommand();
		 invoker.receiveCommand(createSprite); 
	 }
	    	   

}
