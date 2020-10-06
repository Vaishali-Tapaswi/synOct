class Lab5Helper extends Thread{
	
	
	@Override
	public void run() {
		  try{
			  Thread.sleep((int)(Math.random()*1000));
			  System.out.println("Platering phase for " + Thread.currentThread().getName());
			  Thread.sleep((int)(Math.random()*1000));
			  System.out.println("Colouring phase for " + Thread.currentThread().getName());
			  Thread.sleep((int)(Math.random()*1000));
			  System.out.println("Furniture phase for " + Thread.currentThread().getName());
			  
		  }catch(Exception e){
			  System.out.println(e);
		  }
	}
	
	
}
public class Lab5CyclicBarrier {

	public static void main(String[] args) {
for(int i = 1; i<=3;i++){
	Lab5Helper t1 = new Lab5Helper();
	t1.setName("Flat"+i);
	t1.start();
}

	}

}
