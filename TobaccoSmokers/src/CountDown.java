import java.util.Timer;
import java.util.TimerTask;

public class CountDown {
	public interface Listener{
		//倒數結束事件
		public void timeOut();
		//秒數變動事件
		public void onChange(long sec);
	}
	private Listener listener;
	private Timer timer;
	private long delay;
 	private long sec;	 
 	public CountDown() {
		delay = 1;
	}

	//設定傾聽timer事件
	public void addListener(Listener li){
		listener = li;
	}

	public void setComponent(long d){
		delay = d;
	}

	//啟動TIMER
	public void startTimer(int s){ 
		if(timer == null){
			timer = new Timer();
			sec = s;
			TimerTask task = new TimerTask(){
				public void run(){	
					sec -= delay;
					if(listener != null){
						listener.onChange(sec);
					}
					if(sec == 0){
						stopTimer();
					}
				}
			};
			long delaySec = delay * 1000;
			timer.schedule(task, delaySec, delaySec);
		}
	}

	//停止TIMER
	public void stopTimer(){
		if(timer != null){
			timer.cancel();
			timer = null;
		}
	}
}