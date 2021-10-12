package main;
import controller.MakeGameController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import views.View;

public class GameMaker extends Application {
	
	private static Model model; // model
	private static MakeGameController controller; //controller
	private static View view; //view
	
	
	public static Model getModel()
	{
		return model;
	}
	
	public static MakeGameController getMakeGameController()
	{
		return controller;
	}
	
	
	public static View getView()
	{
		return view;
	}
	
	
	@Override
    public void start(Stage primaryStage) throws Exception
	{
		model=new Model();  
		controller=new MakeGameController();
		View.start(primaryStage);
	}

	public static void main(String[] args) {
        launch(args);
    }
}