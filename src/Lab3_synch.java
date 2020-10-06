import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Lab3Support extends Thread{
	private List<String> list ;
	
	public Lab3Support(List<String> list) {
		this.list = list;
	}

	@Override
	public void run() {
		for (int i = 1;i<=100;i++){
				try { Thread.sleep(10); 	} catch (InterruptedException e) { 		e.printStackTrace(); 		}
				list.add(Thread.currentThread().getName()+"-"+i);
		}
	}
}
public class Lab3_synch {
	public static void main(String[] args) {
		System.out.println("Press a number to continue");
		Scanner scanner = new Scanner(System.in);
		scanner.nextInt();
		
	//	List<String> list = new ArrayList<>();
		List<String> list = Collections.synchronizedList(new ArrayList<String>());
		Lab3Support str = new Lab3Support(list);
		str.setName("str");
		str.start();
		Lab3Support mydata = new Lab3Support(list);
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
