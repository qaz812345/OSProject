import java.lang.Thread;

import javafx.scene.image.ImageView;

public class Agent implements Runnable{
	
	private Table table;
	private int item;
	public ImageView img;
	public int xOffset;
	public int yOffset;
	
	public Agent(Table t,int id,ImageView i,int xo,int yo) {
		table=t;
		item=id;
		img=i;
		xOffset=xo;
		yOffset=yo;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep((3-item)*100);
			//img.setVisible(false);
			while(true) {
				int s=table.getPoissonRandom(5);
				if(Table.setedItem[item]==false && Table.itemCount<2){
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
