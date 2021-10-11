package controller;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class MakeGameController {

	    private Parent makerview; //the view
	    private Scene scene;
	    public static final String FILE_PATH = "makerview.fxml";


	    /**
	     * 
	     * @param scene this should contain a VBox with ID vboxPreview so that
	     *              the buttons ribbon/container can be added there.
	     */
	    
	    public MakeGameController(Scene scene)
	    {
	        this.scene = scene;
	        try
	        {
	            //1. Create the game maker view
	            makerview = FXMLLoader.load(getClass().getClassLoader().getResource(FILE_PATH));
	            //2. Select the properties VBox from the scene
	            VBox vboxPreview = (VBox) scene.lookup("#vboxPreview");
	            //3. Add the ribbon to the editor scene
	            vboxPreview.getChildren().add(0, makerview);
	        }
	        catch (IOException e) {
	            System.out.println("Failed to load in " + FILE_PATH);
	            e.printStackTrace();
	        }
	    }

	    /**
	     * Required constructor for when the XML attaches itself to this
	     */
	    public MakeGameController() {
	    }


}
