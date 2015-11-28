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
				semaArray[resource_index-1].acquire();
				cached_resource.reset();
				System.out.println(name + " is smoking the cigaret");
				System.out.println(name + " has stubbed out his cigaret");
				select.release();
			} catch (InterruptedException e) {
				System.out.println(name + "Could not acquire resource number " + resource_index);
			}
			
		}
	}
	
	
}
