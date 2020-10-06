import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
class Lab1SupportCallable implements Callable<String>
{
@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("\n\n in call for " + Thread.currentThread().getName());
		return "Hello"+new Date();
	}	
}
class Lab1Support implements Runnable
{
@Override
public void run() {
	System.out.println("\n\n");
	for (int i = 1;i<10;i++){
		System.out.println("For - " + i +  " in thread " + Thread.currentThread().getName());
		try { Thread.sleep(10); 	} catch (InterruptedException e) { 		e.printStackTrace(); 		}

//		if (i % 5 ==0)
//               		int	k = 5/0;
		
	}
}	
}
public class Lab1 {

	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		System.out.println("Press a number to continue");
		Scanner scanner = new Scanner(System.in);
		scanner.nextInt();
	//	ExecutorService service = Executors.newSingleThreadExecutor();
	//	ExecutorService service = Executors.newFixedThreadPool(2);
		ExecutorService service = Executors.newCachedThreadPool();
		System.out.println("after Thread pool creation");
		service.execute(new Lab1Support());

		service.execute(new Lab1Support());
		service.execute(new Lab1Support());
		service.execute(new Lab1Support());
		service.execute(new Lab1Support());

		service.execute(new Lab1Support());

		/*	service.execute(new Lab1Support());
		Future<String> future = service.submit(new Lab1SupportCallable());
		System.out.println("after future");
		System.out.println("call returned " + future.get());
		*/service.shutdown();
	}
}
