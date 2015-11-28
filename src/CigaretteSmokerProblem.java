import java.util.concurrent.Semaphore;

class CigaretteSmokerProblem {
	
	public static void main(String[] args) {
		int counter;
		Semaphore select = new Semaphore(1);
		Semaphore tobacco = new Semaphore(0);
		Semaphore paper = new Semaphore(0);
		Semaphore spark = new Semaphore(0);
		Agatha agatha = new Agatha(select, tobacco, paper, spark);
		Cached cached_tobacco = new Cached(1, tobacco);
		Cached cached_paper = new Cached(2, paper);
		Cached cached_spark = new Cached(4, spark);
		Smokers horacio = new Smokers(select, cached_resource, 6, "Horacio");
		Smokers arthur = new Smokers(select, cached_resource, 5, "Arthur");
		Smokers edgar = new Smokers(select, cached_resource, 3, "Edgar");
		
	}
	
}