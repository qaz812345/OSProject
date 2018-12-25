
import java.io.File;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
       
		Parent root = FXMLLoader.load(getClass().getResource("IndexPage.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Three Smokers Problem");
		primaryStage.show();
		String musicFile = "bgm_maoudamashii_acoustic41.mp3";
		Media sound = new Media(new File(musicFile).toURI().toString());
		MediaPlayer mediaPlayer = new MediaPlayer(sound);
		mediaPlayer.play();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
}
