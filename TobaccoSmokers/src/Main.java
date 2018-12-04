
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
       
		Parent root = FXMLLoader.load(getClass().getResource("IndexPage.fxml"));
		Scene scenc = new Scene(root);
		primaryStage.setScene(scenc);
		primaryStage.setTitle("Three Smokers Problem");
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
