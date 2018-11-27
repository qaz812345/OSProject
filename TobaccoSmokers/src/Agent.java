import java.lang.Thread;
import java.util.Random;

public class Agent implements Runnable{
	
	private Table table;
	
	static int totalResource=3;
	static int currentResource=0;
	int item;
	
	public Agent(Table t,int i) {
		table=t;
		item=i;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Random r=new Random();
		try {
			while(true) {
				Thread.sleep(getPoissonRandom(5)*1000);
				if(table.setedItem[item]==false) {
					table.setTable(item);
					Thread.sleep(3000);//±j­¢¥¦sleep
				}
			}
		}catch (InterruptedException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static synchronized int getPoissonRandom(double mean) {
		Random r = new Random();
		double L = Math.exp(-mean);
		int k = 0;
		double p = 1.0;
		do {
			p = p * r.nextDouble();
			k++;
		} while (p > L);
		System.out.println("Wait time:"+(k-1));
		return k - 1;
	}
	

}
