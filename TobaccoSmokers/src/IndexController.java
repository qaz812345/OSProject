import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;


public class IndexController implements Initializable {
	
	@FXML
	private Button start=new Button();
	@FXML
	private Label timer=new Label();
	@FXML
	private ImageView cover=new ImageView();
	@FXML
	private ImageView girl= new ImageView();
	@FXML
	private ImageView boy1= new ImageView();
	@FXML
	private ImageView boy2= new ImageView();
	@FXML
	private ImageView tobacoo= new ImageView();
	@FXML
	private ImageView paper= new ImageView();
	@FXML
	private ImageView lighter= new ImageView();
	@FXML
	private ImageView cigeratte= new ImageView();
	@FXML
	private Label count0=new Label();
	@FXML
	private Label count1=new Label();
	@FXML
	private Label count2=new Label();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public void onClick() {
		start.setVisible(false);
		start.setDisable(true);
		FadeTransition ft = new FadeTransition();
		ft.setDelay(Duration.millis(500));
		ft.setNode(cover);
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		ft.play();
		Table table=new Table(timer,tobacoo,paper,lighter,cigeratte);//�@�Ϊ���
		Smoker smoker[]=new Smoker[3];
		Agent agent[]=new Agent[3];
		Thread threads[]=new Thread[6];
		smoker[0]=new Smoker(table,0,girl,"girl",count0,-200,-60);
		smoker[1]=new Smoker(table,1,boy1,"boy1",count1,0,-100);
		smoker[2]=new Smoker(table,2,boy2,"boy2",count2,330,10);
		agent[0]=new Agent(table,0,tobacoo,100,100);
		agent[1]=new Agent(table,1,paper,0,100);
		agent[2]=new Agent(table,2,lighter,-100,100);
		for(int i=0;i<3;i++){
			threads[i]=new Thread(smoker[i]);
			threads[i+3]=new Thread(agent[i]);
			threads[i].start();
			threads[i+3].start();
		}
	}
	
}
