import java.util.Random;
import javafx.application.Platform;
import javafx.scene.control.Label;

public class Table {
	
	private int itemCount=0;// itemCount==2 => available
	private int currentCount=0;
	private final int totalCount=3;
	public static int turn=-1;
	public static boolean[] setedItem= {false,false,false};
	public String[] items= {"tabacco","cigarette paper","match"};
	public Label timeLab;
	
	public Table(Label l) {
		timeLab=l;
	}
	
	public synchronized void setTable(int item,int s){
		try {
			if(itemCount<2){
				setTimeLabel(s);
				Thread.sleep(s*1000);
				itemCount++;
				currentCount+=item;
				setedItem[item]=true;
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
		System.out.println("Table is tidy up!");
		System.out.append('\n');
	}
	
	public String getItem(int i) {
		return items[i];
	}
	
	public synchronized void setTimeLabel(int s) {
		CountDown timer = new CountDown();
		//傾聽計時器timeout事件(可選的事件，不實作也可以使用timer
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
