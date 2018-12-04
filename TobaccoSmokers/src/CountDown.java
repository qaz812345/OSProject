import java.util.Timer;
import java.util.TimerTask;

public class CountDown {
	public interface Listener{
		//�˼Ƶ����ƥ�
		public void timeOut();
		//����ܰʨƥ�
		public void onChange(long sec);
	}
	private Listener listener;
	private Timer timer;
	private long delay;
 	private long sec;	 
 	public CountDown() {
		delay = 1;
	}

	//�]�w��ťtimer�ƥ�
	public void addListener(Listener li){
		listener = li;
	}

	public void setComponent(long d){
		delay = d;
	}

	//�Ұ�TIMER
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

	//����TIMER
	public void stopTimer(){
		if(timer != null){
			timer.cancel();
			timer = null;
		}
	}
}