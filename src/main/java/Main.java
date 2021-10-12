import javafx.application.Application;
import javafx.stage.Stage;
import views.View;

public class Main extends Application {
	
	@Override
    public void start(Stage primaryStage) throws Exception{
		View.start(primaryStage);
	}

	public static void main(String[] args) {
        launch(args);
    }
}