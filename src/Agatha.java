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
			try {
				select.acquire();
			} catch (InterruptedException e) {}
			int item = ran.nextInt(3) + 1;
			switch(item){
			case 1:
				tobacco.release();
				paper.release();
				break;
			case 2:
				tobacco.release();
				spark.release();
				break;
			case 3:
				paper.release();
				spark.release();
				break;
			default:
				break;
			}
		}
	}
	
	
	
	

}
