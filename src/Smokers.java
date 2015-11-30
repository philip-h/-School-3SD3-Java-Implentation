import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Smokers extends Thread {
	private Semaphore select;
	private Cached cached_resource;
	private Semaphore [] semaArray;
	private int resource_index;
	private String name;
	Scanner input;

	public Smokers(Semaphore select, Cached cached_resource, Semaphore[] semaArray, int resource_index, String name) {
		this.select = select;
		this.cached_resource = cached_resource;
		this.semaArray = semaArray;
		this.resource_index = resource_index;
		this.name = name;
		this.input = new Scanner(System.in);
	}

	@Override
	public void run() {
		while(true){

			/* Try to acquire specific index of array */
			if(semaArray[resource_index-1].tryAcquire()) {
				/* If acquired, smoke and stub*/
				cached_resource.reset();
				System.out.println(name + " is smoking the cigarette");
				System.out.println(name + " has stubbed out his cigarette");
				
				/* Release select */
				System.out.println("\nPress enter for Agatha to select two new items\n");
				input.nextLine();
				
				select.release();
			}
		}
	}	
}
