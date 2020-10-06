import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Lab4Support extends Thread{
	private List<String> list ;
	private Lock lock;
	public Lab4Support(List<String> list, Lock lock) {
		this.list = list;
		this.lock = lock;
	}

	@Override
	public void run() {
		for (int i = 1;i<=100;i++){
				lock.lock();
				System.out.println("after lock , take dump");
				try { Thread.sleep(10000); 	} catch (InterruptedException e) { 		e.printStackTrace(); 		}
				
				list.add(Thread.currentThread().getName()+"-"+i);
				lock.unlock();
				System.out.println("after unlock , take dump");
				try { Thread.sleep(10000); 	} catch (InterruptedException e) { 		e.printStackTrace(); 		}
				
		}
	}
}
public class Lab4_lock {
	public static void main(String[] args) {
		System.out.println("Press a number to continue");
		Scanner scanner = new Scanner(System.in);
		scanner.nextInt();
		Lock lock = new ReentrantLock();
		List<String> list = new ArrayList<>();
		Lab4Support str = new Lab4Support(list,lock);
		str.setName("str");
		str.start();
		Lab4Support mydata = new Lab4Support(list,lock);
		mydata.setName("mydata");
		mydata.start();
		try {
			str.join();
			mydata.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(list);
		System.out.println(list.size());
	}
}
