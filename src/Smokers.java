import java.util.concurrent.Semaphore;

public class Smokers extends Thread {
	private Semaphore select;
	private Cached cached_resource;
	private Semaphore [] semaArray;
	private int resource_index;
	private String name;

	public Smokers(Semaphore select, Cached cached_resource, Semaphore[] semaArray, int resource_index, String name) {
		this.select = select;
		this.cached_resource = cached_resource;
		this.semaArray = semaArray;
		this.resource_index = resource_index;
		this.name = name;
	}

	@Override
	public void run() {
		while(true){
			try {
				/* Try to acquire specific index of array */
				if(semaArray[resource_index-1].tryAcquire()) {
					/* If acquired, smoke and stub*/
					cached_resource.reset();
					System.out.println(name + " is smoking the cigarette");
					System.out.println(name + " has stubbed out his cigarette");
					//Thread.sleep(1000);
					/* Release select */
					select.release();
				}
				
			} catch (Exception e) {
				System.out.println(name + "Could not acquire resource number " + resource_index);
			}
			
		}
	}	
}
