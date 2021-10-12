package controller;

import java.io.IOException;

import command.CreateSpriteCommand;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
//import javafx.scene.Scene;

public class MakeGameController {

	    private Parent makerview; //the view
	    public static final String FILE_PATH = "makerview.fxml";


	    /**
	     * 
	     * @param scene this should contain a VBox with ID vboxPreview so that
	     *              the buttons ribbon/container can be added there.
	     */
	    
	    public MakeGameController()
	    {
	        try
	        {
	            //1. Create the game maker view
	            makerview = FXMLLoader.load(getClass().getClassLoader().getResource(FILE_PATH));
	           // TODO 
	        }
	        catch (IOException e) {
	            System.out.println("Failed to load in " + FILE_PATH);
	            e.printStackTrace();
	        }
	    }  
	    
	    	public void onClickCreateSprite(ActionEvent event)
	    	{
	    	}


}
