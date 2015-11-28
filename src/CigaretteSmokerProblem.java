import java.util.concurrent.Semaphore;

class CigaretteSmokerProblem {
	
	public static void main(String[] args) {
		Semaphore[] semaArray = new Semaphore[6];
		//Initialize our resource 6 semaphores to 0 in our semaphore array
		for (int i=0; i<6; i++){
			semaArray[i] = new Semaphore(0);
		}
		
		int counter;
		Semaphore select = new Semaphore(1);
		Semaphore tobacco = new Semaphore(0);
		Semaphore paper = new Semaphore(0);
		Semaphore spark = new Semaphore(0);
		Agatha agatha = new Agatha(select, tobacco, paper, spark);
		Cached cached_tobacco = new Cached(1, tobacco, semaArray);
		Cached cached_paper = new Cached(2, paper, semaArray);
		Cached cached_spark = new Cached(4, spark, semaArray);
		/* Serves to pass over the counter variable as it is static in Cached*/
		Cached cached_resource = new Cached(0, null, null);
		
		Smokers horacio = new Smokers(select, cached_resource, semaArray, 6, "Horacio");
		Smokers arthur = new Smokers(select, cached_resource, semaArray, 5, "Arthur");
		Smokers edgar = new Smokers(select, cached_resource, semaArray, 3, "Edgar");
	}
	
}