import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Table {
	
	private int currentCount=0;
	private final int totalCount=3;
	public static int itemCount=0;// itemCount==2 => available
	public static int turn=-1;
	public static final int sleepTime=3;
	public static boolean[] setedItem= {false,false,false};
	public String[] items= {"tabacco","cigarette paper","match"};
	public Label timeLab;
	public ImageView tobacooImg,paperImg,lighterImg,cigaretteImg;
	
	public Table(Label l,ImageView t,ImageView p,ImageView m,ImageView c) {
		timeLab=l;
		tobacooImg=t;
		paperImg=p;
		lighterImg=m;
		cigaretteImg=c;
	}
	
	public synchronized void setTable(int item,int s){
		try {
			if(itemCount<2){
				setTimeLabel(s);
				Thread.sleep(s*1000);
				itemCount++;
				currentCount+=item;
				setedItem[item]=true;
				if(item==0)
					Platform.runLater(()->{
						fade(tobacooImg,0.0,1.0);
						move(tobacooImg,-150,110);
					});
				else if(item==1)
					Platform.runLater(()->{
						fade(paperImg,0.0,1.0);
						move(paperImg,0,125);
					});
				else
					Platform.runLater(()->{
						fade(lighterImg,0.0,1.0);
						move(lighterImg,110,130);
					});
				Thread.sleep(2000);
				System.out.println(items[item]+" is set!");
				if(itemCount==2) {		
					turn=totalCount-currentCount;
					System.out.println("Is "+turn+"'s turn!");		
				}
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public synchronized void tidyTable() {
		itemCount=0;
		currentCount=0;
		turn=-1;
		for(int i=0;i<3;i++) {
			setedItem[i]=false;
		}
		Platform.runLater(()->move(tobacooImg,0,40));
		Platform.runLater(()->move(paperImg,0,40));
		Platform.runLater(()->move(lighterImg,0,40));
		Platform.runLater(()->move(cigaretteImg,0,70));
		System.out.println("Table is tidy up!");
		System.out.append('\n');
		
	}
	
	public String getItem(int i) {
		return items[i];
	}
	
	public void makeCigarette(int x,int y) {
		fade(cigaretteImg,0.0,1.0);
		if(tobacooImg.getOpacity()==1.0) {
			fade(tobacooImg,1.0,0.0);
		}
		if(paperImg.getOpacity()==1.0) {
			fade(paperImg,1.0,0.0);
		}
		if(lighterImg.getOpacity()==1.0) {
			fade(lighterImg,1.0,0.0);
		}
		move(cigaretteImg,x,y);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fade(cigaretteImg,1.0,0.0);
	}
	
	public void move(ImageView img,int x,int y) {
		TranslateTransition tt = new TranslateTransition();
		tt.setDelay(Duration.millis(1000));
		tt.setNode(img);
		tt.setToX(x);
		tt.setToY(y);
		tt.play();
	}
	
	private void fade(ImageView img,double from,double to) {
		FadeTransition ft = new FadeTransition();
		ft.setDelay(Duration.millis(500));
		ft.setNode(img);
		ft.setFromValue(from);
		ft.setToValue(to);
		ft.play();
	}
	
	public synchronized void setTimeLabel(int s) {
		CountDown timer = new CountDown();
		//��ť�p�ɾ�timeout�ƥ�(�i�諸�ƥ�A����@�]�i�H�ϥ�timer
		timer.addListener(new CountDown.Listener() {
			@Override
			public void timeOut() {
				//Platform.runLater(()->timeLab.setText("00:00"));
			}

			@Override
			public void onChange(long sec) {
				//System.out.println("sec=>00:"+((sec<10)?"0":"")+String.valueOf(sec));
				Platform.runLater(()->timeLab.setText("00:"+((sec<10)?"0":"")+String.valueOf(sec)));
			}
		});
		timer.startTimer(s);
	}
	
	public synchronized int getPoissonRandom(double mean) {
		Random r = new Random();
		double L = Math.exp(-mean);
		int k = 0;
		double p = 1.0;
		do {
			p = p * r.nextDouble();
			k++;
		} while (p > L);
		//System.out.println("Wait time:"+(k-1));
		return k-1;
	}

}
