import java.util.Random;
import java.util.concurrent.Semaphore;

public class Agatha extends Thread {
	Semaphore select;
	Semaphore tobacco, paper, spark;
	Random ran;
	
	// Constructor for our agent
	/* Parameters: 
	 * Semaphore select: determines whether the agent should be selecting a new item or not
	 * Semaphore tabacco/paper/spark used to release the resources selected by the agent
	 */
	public Agatha(Semaphore select, Semaphore tobacco, Semaphore paper, Semaphore spark) {
		this.select = select;
		this.tobacco = tobacco;
		this.paper = paper;
		this.spark = spark;
		ran = new Random();
	}

	@Override
	public void run() {
		while(true) {

			try {
				select.acquire();
				Thread.sleep(1000);

			} catch (InterruptedException e) {
				System.err.println("Could not acquire select process");
			}
			
			/* Generate a random number to see which two items should be released */
			int item = ran.nextInt(3) + 1;
			/* Release the items */
			switch(item){
			case 1:
				tobacco.release();
				paper.release();
				System.out.println("Agatha put tobacco and paper on the table");
				break;
			case 2:
				tobacco.release();
				spark.release();
				System.out.println("Agatha put tobacco and spark on the table");
				break;
			case 3:
				paper.release();
				spark.release();
				System.out.println("Agatha put paper and spark on the table");
				break;
			default:
				break;
			}
		}
	}

}
