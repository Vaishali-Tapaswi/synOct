import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Lab1Support extends Thread{
	private List<String> list ;
	
	public Lab1Support(List<String> list) {
		this.list = list;
	}

	@Override
	public void run() {
		for (int i = 1;i<=100;i++){
			synchronized(list){
				// just to take and show thread dump
				try { Thread.sleep(1000); 	} catch (InterruptedException e) { 		e.printStackTrace(); 		}
				list.add(Thread.currentThread().getName()+"-"+i);
	}
		}
	}
}
public class Lab1_synchronized {
	public static void main(String[] args) {
		System.out.println("Press a number to continue");
		Scanner scanner = new Scanner(System.in);
		scanner.nextInt();
		
		List<String> list = new ArrayList<>();
		Lab1Support str = new Lab1Support(list);
		str.setName("str");
		str.start();
		Lab1Support mydata = new Lab1Support(list);
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
