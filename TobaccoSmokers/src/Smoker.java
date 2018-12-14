import java.lang.Thread;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.util.Duration;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Smoker implements Runnable{
	
	private Table table;
	private int item;
	private int smokeCounter;
	public ImageView img;
	final public ArrayList<Image> imgList;
	public String name;
	public Label countLab;
	public int xOffset;
	public int yOffset;
	public int i=0;
	
	public Smoker(Table t,int id,ImageView i,String s,Label c,int x,int y) {
		table=t;
		item=id;
		smokeCounter=0;
		img=i;
		countLab=c;
		name=new String (s);
		imgList= new ArrayList<>();
		imgList.add(new Image("file:res/drawable/"+name+"0.png"));
		imgList.add(new Image("file:res/drawable/"+name+"1.png"));
		imgList.add(new Image("file:res/drawable/"+name+"2.png"));
		imgList.add(new Image("file:res/drawable/"+name+"2.png"));
		imgList.add(new Image("file:res/drawable/"+name+"2.png"));
		imgList.add(new Image("file:res/drawable/"+name+"1.png"));
		imgList.add(new Image("file:res/drawable/"+name+"0.png"));
		xOffset=x;
		yOffset=y;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep((3-item)*100);
			while(true) {
				if(Table.turn==item) {
					synchronized(table) {						
						Platform.runLater(()->table.makeCigarette(xOffset,yOffset));
						Thread.sleep(2000);
						Platform.runLater(()->{
					    	Duration sec = new Duration(400);
						    KeyFrame keyFrame = new KeyFrame(sec,new EventHandler<ActionEvent>(){
								@Override
								public void handle(ActionEvent arg0) {
									// TODO Auto-generated method stub
									img.setImage(imgList.get(i++));
									if (i >= imgList.size()) {
										i = 0;
									}
								}
						    	
						    });
						    Timeline timeline = new Timeline(keyFrame);
						    timeline.setCycleCount(7);
						    timeline.playFromStart();
						});
						table.setTimeLabel(Table.sleepTime);
						Thread.sleep(Table.sleepTime*1000);
						System.out.println("Smoker with "+table.getItem(item)+" is smoking...");
						counterIncrease();
						Platform.runLater(()->countLab.setText(String.valueOf(smokeCounter)));
						System.out.println("Smoker with "+table.getItem(item)+" smokes "+smokeCounter+" time.");
						table.tidyTable();
					}
				}else {
					Thread.sleep(100);
				}	
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void counterIncrease() {
		smokeCounter++;
	}	
	
}


