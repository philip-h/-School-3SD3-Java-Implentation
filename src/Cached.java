import java.util.concurrent.Semaphore;

public class Cached extends Thread {
	Semaphore resource;
	Semaphore[] semaArray;
	Semaphore mutex;
	int resourceId;
	static int counter = 0;

	public Cached(int resourceId, Semaphore resource, Semaphore[] semaArray) {
		this.resourceId = resourceId;
		this.resource = resource;
		this.semaArray = semaArray;
		this.mutex = new Semaphore(1);
	}

	public void reset() {
		Cached.counter = 0;
	}

	public Semaphore[] getSemaArray() {
		return this.semaArray;
	}

	public void run() {
		while (true) {
			try {
				/* down on resource */
				this.resource.acquire();

				/* down on mutex */
				this.mutex.acquire();

				/* modify counter depending on resource */
				Cached.counter += this.resourceId;

				/* release our resource semaphores */
				semaArray[counter-1].release();
				 /* up on mutex */
				this.mutex.release();
				
				//Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println("Could not acquire resource or mutex semaphore");
			}
		}
	}
}
