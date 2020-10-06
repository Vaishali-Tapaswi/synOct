import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

class Lab2Support extends Thread
{
//	static String finalloc;
//	static boolean contflag=true;
	private String str;
	private CountDownLatch latch;
public Lab2Support(String str, CountDownLatch latch) {
		super(str);
		this.str = str;
		this.latch = latch;
	}

@Override
public void run() {
	for (int i = 0;i<10 && latch.getCount()>0;i++){
		System.out.println("' " +  str  +"---"+  i +"' ");
		try { Thread.sleep((int)(Math.random()*100)); 	} catch (InterruptedException e) { 		e.printStackTrace(); 		}
	}
	if (latch.getCount() >0)
		System.out.println("Currentn latch status  = " + latch.getCount());
		Lab2_latch2.finalloc= Thread.currentThread().getName();
		latch.countDown();
}
}

public class Lab2_latch2 {
	static String finalloc = null;
public static void main(String[] args) throws Exception {
	System.out.println("Press a number to continue");
	Scanner scanner = new Scanner(System.in);
	scanner.nextInt();
	CountDownLatch latch = new CountDownLatch(1);
	for (int i =1;i<= 50;i++){
		Lab2Support t1 = new Lab2Support("Loc"+i, latch);
		t1.start();
	}
	latch.await();
	System.out.println("One Thread is over");
	System.out.println("\n\n*****Final Selection is " + finalloc + "*****");
	
}
}
