import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
class Lab2Support implements Runnable
{
@Override
public void run() {
	System.out.println("\n\n");
	for (int i = 1;i<10;i++){
		System.out.println("For - " + i +  " in thread " + Thread.currentThread().getName() + ", " + new Date());
		try { Thread.sleep(200); 	} catch (InterruptedException e) { 		e.printStackTrace(); 		}
	}
}	
}

public class Lab2 {
public static void main(String[] args) {
	// ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();
	 ScheduledExecutorService ses = Executors.newScheduledThreadPool(3);
	 System.out.println("In main " + new Date());
//	 ses.schedule(new Lab2Support(),10,TimeUnit.SECONDS);
	ses.scheduleAtFixedRate(new Lab2Support(),5, 1,TimeUnit.SECONDS);
	// ses.scheduleWithFixedDelay(new Lab2Support(),5, 5,TimeUnit.SECONDS);
}
}
