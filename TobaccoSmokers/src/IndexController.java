

import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Node;
import javafx.scene.Parent;


public class IndexController implements Initializable {
	
	@FXML
	private Button start=new Button();
	private Label time=new Label();

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Table table=new Table();
		Smoker smoker[]=new Smoker[3];
		Agent agent[]=new Agent[3];
		Thread threads[]=new Thread[6];
		for(int i=0;i<3;i++) {
			smoker[i]=new Smoker(table,i);
			agent[i]=new Agent(table,i);
			threads[i]=new Thread(smoker[i]);
			threads[i+3]=new Thread(agent[i]);
			threads[i].start();
			threads[i+3].start();
		}
	}
	@FXML
	public void onStartClick(ActionEvent event) throws IOException{
		start.setText("GO!");
	}
	
}
