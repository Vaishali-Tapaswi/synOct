import java.util.Scanner;

class Lab4Support extends Thread{
	private String str1;
	private String str2;
	public Lab4Support(String str1, String str2) {
		super();
		this.str1 = str1;
		this.str2 = str2;
	}
	
	@Override
	public void run() {
		synchronized(str1)
		{
			System.out.println(str1 + " locked and waiting for " + str2 + " lock");
			try { Thread.sleep(5000); 	} catch (InterruptedException e) { 		e.printStackTrace(); 		}
			synchronized (str2){
				System.out.println(str1 + ", " + str2 + " locked ");
				try { Thread.sleep(5000); 	} catch (InterruptedException e) { 		e.printStackTrace(); 		}
			}
			System.out.println("released    " +  str2 + " lock ..... will be releasing " + str1 );
			try { Thread.sleep(5000); 	} catch (InterruptedException e) { 		e.printStackTrace(); 		}
		}
	}
}
public class Lab4_deadlock {

	public static void main(String[] args) {
		String str1= "StringOne";
		String str2 = "StringTwo";
		System.out.println("Press a number to continue");
		Scanner scanner = new Scanner(System.in);
		scanner.nextInt();
	
		Lab4Support t1 = new Lab4Support(str1, str2);
		
		t1.start();
		Lab4Support t2 = new Lab4Support(str2, str1);
		t2.start();

		
	}

}
