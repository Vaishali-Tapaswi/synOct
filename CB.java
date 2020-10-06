import java.util.concurrent.CyclicBarrier;

class Lab5Helper extends Thread{
	private CyclicBarrier barrier ;
	
	public Lab5Helper(CyclicBarrier barrier) {
		super();
		this.barrier = barrier;
	}

	@Override
	public void run() {
		  try{
			  Thread.sleep((int)(Math.random()*1000));
			  System.out.println("Plastering phase for " + Thread.currentThread().getName());
			  barrier.await();
			  Thread.sleep((int)(Math.random()*1000));
			  System.out.println("Colouring phase for " + Thread.currentThread().getName());
			  barrier.await();
			  Thread.sleep((int)(Math.random()*1000));
			  System.out.println("Furniture phase for " + Thread.currentThread().getName());
			  
		  }catch(Exception e){
			  System.out.println(e);
		  }
	}
	
	
}
public class Lab5CyclicBarrier {
	
	public static void main(String[] args) {
		CyclicBarrier barrier = new CyclicBarrier(3);
for(int i = 1; i<=3;i++){
	Lab5Helper t1 = new Lab5Helper(barrier);
	t1.setName("Flat"+i);
	t1.start();
}

	}

}
