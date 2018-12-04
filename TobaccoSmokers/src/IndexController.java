import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class IndexController implements Initializable {
	
	@FXML
	private Label time=new Label();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		Table table=new Table(time);//共用物件
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
	
}
