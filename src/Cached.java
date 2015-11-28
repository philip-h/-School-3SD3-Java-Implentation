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
	
	public void reset(){
		this.counter = 0;
	}
	
	public Semaphore[] getSemaArray(){
		return this.semaArray;
	}

	public void run()
	{
		while(true) {
			try {
				//down on resource
				this.resource.acquire();
				
				// down on mutex
				this.mutex.acquire();
				
				// modify counter depending on resource
				this.counter += this.resourceId;
				
				//release our resource semaphores
				for (int i=0; i<6; i++){
					semaArray[i].release();
				}
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				mutex.release();
			}			
		}
	} 
}
