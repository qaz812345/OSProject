import java.lang.Thread;

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
		try {
			while(true) {
				if(Table.turn==ID) {
					synchronized(table) {
						//Smoking animation here
						System.out.println("Smoker with "+table.getItem(ID)+" is smoking...");
						table.setTimeLabel(3);
						Thread.sleep(3000);
						counterIncrease();
						//Set smokeCounter on GUI here
						System.out.println("Smoker with "+table.getItem(ID)+" smokes "+smokeCounter+" time.");
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
	
	private int getCount() {
		return smokeCounter;
	}

}
