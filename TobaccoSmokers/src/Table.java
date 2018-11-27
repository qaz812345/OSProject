public class Table {
	
	private int itemCount=0;// itemCount==2 => available
	private int currentCount=0;
	private final int totalCount=3;
	public static int turn=-1;
	public static boolean[] setedItem= {false,false,false};
	public  String[] items= {"tabacco","cigarette paper","match"};
	
	public synchronized void setTable(int item){
		
		if(itemCount==2) {		
			turn=totalCount-currentCount;
			System.out.println("Is "+turn+"'s turn!");
			itemCount=0;
		}else if(itemCount<2){
			itemCount++;
			currentCount+=item;
			setedItem[item]=true;
			System.out.println(items[item]+" is set!");
		}else {
			;
		}
	}
	
	public synchronized void tidyTable() {
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
}
