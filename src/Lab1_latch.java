import java.util.Scanner;
import java.util.concurrent.CountDownLatch;

class Lab1Support extends Thread
{
	private String str;
	private CountDownLatch latch;
	
public Lab1Support(String str, CountDownLatch latch) {
		super();
		this.str = str;
		this.latch = latch;
	}

@Override
public void run() {
	for (int i = 0;i<10 ;i++){
		System.out.print(str);
		try { Thread.sleep((int)(Math.random()*1000)); 	} catch (InterruptedException e) { 		e.printStackTrace(); 		}
	}
	System.out.println("Currentn latch status  = " + latch.getCount());
	latch.countDown();
}	
}

public class Lab1_latch {

	
public static void main(String[] args) throws InterruptedException {
	System.out.println("Press a number to continue");
	Scanner scanner = new Scanner(System.in);
	//scanner.nextInt();
	
	CountDownLatch latch = new CountDownLatch(3);
	Lab1Support t1 = new Lab1Support("A", latch);
	t1.setName("AThread");
	Lab1Support t2 = new Lab1Support("B",latch);
	t2.setName("BThread");
	Lab1Support t3 = new Lab1Support("C",latch);
	t3.setName("CThread");
	t1.start();
	t2.start();
	t3.start();
	System.out.println(" before await");
	latch.await();
	System.out.println("all three threads  completed execution");
/*	try {
		t1.join();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println("end of  main  after completing t1 ");
	*/
}
}

