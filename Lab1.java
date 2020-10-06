import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;

class MyRecursiveAction extends RecursiveAction {

    private List<Integer> inputlist ;


    public MyRecursiveAction(List<Integer> inputlist) {
		super();
		this.inputlist = inputlist;
		System.out.println("MyRecursiveAction Constructor " + inputlist.size());
	}

    private List<MyRecursiveAction> createSubtasks(List<Integer> list) {
        List<MyRecursiveAction> subtasks =
            new ArrayList<MyRecursiveAction>();
        
        MyRecursiveAction subtask1 = new MyRecursiveAction(list.subList(0, list.size()/2));
        MyRecursiveAction subtask2 = new MyRecursiveAction(list.subList(list.size()/2, list.size()));

        subtasks.add(subtask1);
        subtasks.add(subtask2);

        return subtasks;
    }

    
	@Override
    protected void compute() {

        //if work is above threshold, break tasks up into smaller tasks
		if (this.inputlist.size() > 20)
		{
			List<MyRecursiveAction> subtasks = new ArrayList<MyRecursiveAction>(); 
					subtasks.addAll(createSubtasks(inputlist));
			 for(RecursiveAction subtask : subtasks){
				 System.out.println("Forking !!!");
				 try { Thread.sleep(100); 	} catch (InterruptedException e) { 		e.printStackTrace(); 		}
	                subtask.fork();
	            }
	  } else {
		  for (Integer i : inputlist){
			  try { Thread.sleep(100); 	} catch (InterruptedException e) { 		e.printStackTrace(); 		}

			  if (i < 999 && i > 100)
				  	System.out.println(i + "  filtered by " + Thread.currentThread().getName());
		  }
        }
    }
}
public class ForkJoinDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Press a number to continue");
		Scanner scanner = new Scanner(System.in);
		scanner.nextInt();
	
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i< 4000;i++)
		{
			list.add((int)(Math.random()*1000));
		}
	
		ForkJoinPool pool = new ForkJoinPool(4);
		pool.invoke(new MyRecursiveAction(list));
	}

}
