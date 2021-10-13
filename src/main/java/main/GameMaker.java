package main;
import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import model.Model;
import views.View;

public class GameMaker extends Application {
	
	private static Model model = new Model(); // model
	private static Controller controller = new Controller(); //controller
	private static View view = new View(); //view
	
	public static Model getModel()
	{
		return model;
	}
	
	public static Controller getMakeGameController()
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
		controller=new Controller();
		View.start(primaryStage);
	}

	public static void main(String[] args) {
        launch(args);
    }
}