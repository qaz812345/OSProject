import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Table {
	
	private static final int totalCount=3;
	private static int currentCount=0;
	public static int itemCount=0;// itemCount==2 => available
	public static int turn=-1;
	public static final int smokeTime=3;
	public static boolean[] setedItem= {false,false,false};
	public static String[] items= {"tobacco","cigarette paper","match"};
	public static Label timeLab;
	public static ImageView tobaccoImg,paperImg,lighterImg,cigaretteImg;
	
	public Table(Label l,ImageView t,ImageView p,ImageView m,ImageView c) {
		timeLab=l;
		tobaccoImg=t;
		paperImg=p;
		lighterImg=m;
		cigaretteImg=c;
	}
	
	public synchronized static void setTable(int item,int s){
		try {
			if(itemCount<2){
				setTimeLabel(s);
				Thread.sleep(s*1000);
				itemCount++;
				currentCount+=item;
				setedItem[item]=true;
				if(item==0)
					Platform.runLater(()->{
						fade(tobaccoImg,0.0,1.0);
						move(tobaccoImg,-150,110);
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
	
	public synchronized static void tidyTable() {
		itemCount=0;
		currentCount=0;
		turn=-1;
		for(int i=0;i<3;i++) {
			setedItem[i]=false;
		}
		Platform.runLater(()->move(tobaccoImg,0,40));
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
		if(tobaccoImg.getOpacity()==1.0) {
			fade(tobaccoImg,1.0,0.0);
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
	
	public static void move(ImageView img,int x,int y) {
		TranslateTransition tt = new TranslateTransition();
		tt.setDelay(Duration.millis(1000));
		tt.setNode(img);
		tt.setToX(x);
		tt.setToY(y);
		tt.play();
	}
	
	private static void fade(ImageView img,double from,double to) {
		FadeTransition ft = new FadeTransition();
		ft.setDelay(Duration.millis(500));
		ft.setNode(img);
		ft.setFromValue(from);
		ft.setToValue(to);
		ft.play();
	}
	
	public synchronized static void setTimeLabel(int s) {
		CountDown timer = new CountDown();
		timer.addListener(new CountDown.Listener() {

			@Override
			public void onChange(long sec) {
				//System.out.println("sec=>00:"+((sec<10)?"0":"")+String.valueOf(sec));
				if(sec>=0)
					Platform.runLater(()->timeLab.setText("00:"+((sec<10)?"0":"")+String.valueOf(sec)));
			}
		});
		timer.startTimer(s);
	}
	
	public int getPoissonRandom(double mean) {
		Random r = new Random();
		double L = Math.exp(-mean);
		int k = 0;
		double p = 1.0;
		do {
			p = p * r.nextDouble();
			k++;
		} while (p > L);
		return k-1;
	}

}
