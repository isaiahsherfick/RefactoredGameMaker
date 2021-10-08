import javafx.application.Application;
import javafx.stage.Stage;
import views.MakerView;

public class Main extends Application {
	
	@Override
    public void start(Stage primaryStage) throws Exception{
		MakerView.start(primaryStage);
	}

}