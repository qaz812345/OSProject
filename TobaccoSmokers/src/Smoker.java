import java.lang.Thread;
import java.util.Random;

public class Smoker implements Runnable{
	
	private Table table;
	
	private int ID;
	private int smokeCounter;

	
	public Smoker(Table t,int id) {
		table=t;
		ID=id;
		smokeCounter=0;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Random r=new Random();
		try {
			while(true) {
				if(table.turn==ID) {
					synchronized(table) {	
						counterIncrease();
						/*for(int i=0;i<210;i++) {
							for(int j=0;j<=i;j++) {
								System.out.append('*');
							}
							Thread.sleep(10);
							System.out.append('\n');
						}*/
						System.out.println("Smoker with "+table.getItem(ID)+" smokes "+smokeCounter+" time.");
						table.tidyTable();
						Thread.sleep(5000);
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
	
	public int getCount() {
		return smokeCounter;
	}

}
