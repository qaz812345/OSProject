

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.event.*;
import javafx.scene.control.Button;;



public class IndexController implements Initializable {
	@FXML
	private Button start=new Button();
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}
	@FXML
	public void onStartClick(ActionEvent event) {
		start.setText("GO!");
	}
	

}
