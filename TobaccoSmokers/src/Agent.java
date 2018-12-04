import java.lang.Thread;

public class Agent implements Runnable{
	
	private Table table;
	private int item;
	
	public Agent(Table t,int i) {
		table=t;
		item=i;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true) {
				int s=table.getPoissonRandom(5);
				if(Table.setedItem[item]==false){
					table.setTable(item,s);
				}else {
					Thread.sleep(s*1000);
				}
			}
		}catch (InterruptedException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
