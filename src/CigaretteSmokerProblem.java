import java.util.concurrent.Semaphore;

class CigaretteSmokerProblem {
	protected char[] buf;
	protected int in = 0;
	protected int out = 0;
	protected int count = 0;
	protected int size;

	Semaphore full; // counts number of items
	Semaphore empty;// counts number of spaces

	CigaretteSmokerProblem(int size) {
		this.size = size;
		buf = new char[size];
		full = new Semaphore(0);
		empty = new Semaphore(size);
	}

	synchronized public void put(char o) throws InterruptedException {
		empty.acquire();
		synchronized(this) { //deadlock was here, protect!
			buf[in] = o;
			++count;
			in = (in + 1) % size;
		}
		full.release();
	}

	synchronized public char get() throws InterruptedException {
		full.acquire();
		char o = buf[out];
		buf[out] = 0;
		--count;
		out = (out + 1) % size;
		empty.release();
		return (o);
	}
}