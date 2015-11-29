import java.util.Random;
import java.util.concurrent.Semaphore;

public class Agatha extends Thread {
	Semaphore select;
	Semaphore tobacco, paper, spark;
	Random ran;
	
	
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
			System.out.println("a");
			try {
				select.acquire();
				System.out.println("tried");
			} catch (InterruptedException e) {
				System.err.println("Could not acquire select process");
			}
			int item = ran.nextInt(3) + 1;
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
