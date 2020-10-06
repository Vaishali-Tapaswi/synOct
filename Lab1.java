import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;
class MyRecursiveTask11 extends RecursiveTask<List<Integer>>
{
	List<Integer> list;
	Predicate<Integer> filter;
	
	public MyRecursiveTask11(List<Integer> list, Predicate<Integer> filter) {
		super();
		this.list = list;
		this.filter = filter;
	}

	 private List<MyRecursiveTask11> forkthetasks() {
	        List<MyRecursiveTask11> subtasks =     new ArrayList<MyRecursiveTask11>();
	
	        MyRecursiveTask11 subtask1 = new MyRecursiveTask11(list.subList(0, list.size()/2), filter);
	        MyRecursiveTask11 subtask2 = new MyRecursiveTask11(list.subList( list.size()/2, list.size()), filter);
	        subtasks.add(subtask1);
	        subtasks.add(subtask2);
	        return subtasks;
	    }
	protected List<Integer> compute() {
	
		 //if work is above threshold, break tasks up into smaller tasks
        if(this.list.size() > 100) {
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            System.out.println("Splitting workLoad : " + this.list.size());

            List<MyRecursiveTask11> subtasks =   forkthetasks();

            for(MyRecursiveTask11 subtask : subtasks){
                subtask.fork();
            }
        	List<Integer> list1 = new ArrayList<Integer>(); 
          
            for(MyRecursiveTask11 subtask : subtasks) {
               list1.addAll(subtask.join());
            }
            return list1;

        } else {
            System.out.println("Doing workLoad myself: " + this.list);
        	List<Integer> list1 = new ArrayList<Integer>(); 
        	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	for (int i = 0; i< list.size();i++){
    			if (filter.test(list.get(i)))
    					list1.add(list.get(i));
    		}
    		return list1;
        }
	}
	
}

public class ForkJoinDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Enter a number to start ...");
		Scanner scanner = new Scanner(System.in);
		scanner.nextInt();
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i< 40000;i++){
				list.add((int)(Math.random() *1000));
		}
		System.out.println(list);
		Predicate<Integer> pred = (num)->num>200;
		ForkJoinPool forkJoinPool = new ForkJoinPool(4);
		MyRecursiveTask11 task = new MyRecursiveTask11(list, pred);
		List<Integer> sortedlist = forkJoinPool.invoke(task);
		System.out.println(sortedlist);
	}

}
