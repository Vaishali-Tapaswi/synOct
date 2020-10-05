import java.util.Scanner;

class Account {
	private String name;
	private int balance;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Account [name=" + name + ", balance=" + balance + "]";
	}
	
}
class Deposit extends Thread{
private Account acc;

public Deposit(Account acc) {
	super();
	this.acc = acc;
}
@Override
	public void run() {
		for (int i = 1;i <= 10;i++){
		synchronized (acc) {
			int bal = this.acc.getBalance();
			bal+=100;
			this.acc.setBalance(bal);
			System.out.println("Deposit, current i =  " +  i + ", current balance = " + this.acc.getBalance());
			if (this.acc.getBalance()>=100){
				System.out.println("Notifying acc... ");
				acc.notify();
				
			}
			
		}
		}
	}
}	
class Widraw extends Thread{
	private Account acc;

	public Widraw(Account acc) {
		super();
		this.acc = acc;
	}
	@Override
		public void run() {
			for (int i = 1;i <= 10;i++){
				synchronized (acc) {
					if (this.acc.getBalance() < 100)
					{
						System.out.println("Current Balance is less than 100");
						try {
							
							acc.wait(20000);
							System.out.println(" after wait ");
					//		try { Thread.sleep(10000); 	} catch (InterruptedException e) { 		e.printStackTrace(); 		}

						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					int bal = this.acc.getBalance();
					bal-=100;
					this.acc.setBalance(bal);
					System.out.println("Widraw, current i =  " +  i + ", current balance = " + this.acc.getBalance());	
				}
			}
		}	
}		
public class Lab2_waitnotify {

	public static void main(String[] args) {
		System.out.println("Press a number to continue");
		Scanner scanner = new Scanner(System.in);
		scanner.nextInt();
		
		Account acc = new Account();
	 Deposit d = new Deposit(acc);
	 Widraw w = new Widraw(acc);
	 d.start();
	 w.start();

	}

}
