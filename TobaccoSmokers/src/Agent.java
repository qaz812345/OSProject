import java.lang.Thread;

public class Agent implements Runnable{
	
	private Table table;
	private int item;
	
	public Agent(Table t,int id) {
		table=t;
		item=id;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep((3-item)*100);
			while(true) {
				int s=table.getPoissonRandom(5);
				if(Table.setedItem[item]==false && Table.itemCount<2){
					Table.setTable(item,s);		
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
