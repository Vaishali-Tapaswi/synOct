import java.util.ArrayList;
import java.util.List;

class Lab1Support extends Thread{
	private List<String> list ;
	
	public Lab1Support(List<String> list) {
		this.list = list;
	}

	@Override
	public void run() {
		for (int i = 1;i<=100;i++){
			list.add(Thread.currentThread().getName()+"-"+i);
		}
	}
}
public class Lab1 {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		Lab1Support str = new Lab1Support(list);
		str.setName("str");
		str.start();
		Lab1Support mydata = new Lab1Support(list);
		str.setName("mydata");
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
