
import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException {
       
		/*Text title=new Text("Hello World");
		Button start=new Button();
		ImageView back = new ImageView("file:res/drawable/indexBackground.png");
		title.setX(190);
		title.setY(150);
		start.setText("start!");
		start.setLayoutX(200);
		start.setLayoutY(200);
		start.setOnAction(new EventHandler<ActionEvent>() {
			int count=1;
			@Override
			public void handle(ActionEvent event) {
				if(count%2==1)
					start.setText("GO!");
				else
					start.setText("start!");
				count++;
			}
		});
		back.setFitHeight(100);
		back.setFitWidth(100);
		back.setX(0);
		back.setY(200);
		Group root = new Group();
	    Scene scene = new Scene(root,500,500);
        HBox box = new HBox();
        box.getChildren().add(title);
        box.getChildren().add(start);
        box.getChildren().add(back);
        root.getChildren().add(box);
		primaryStage.setTitle("Three Smokers Problem");
		primaryStage.setScene(scene);
		primaryStage.show();*/
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
