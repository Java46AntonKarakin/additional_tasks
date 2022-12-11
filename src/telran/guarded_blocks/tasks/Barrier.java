package telran.guarded_blocks.tasks;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Barrier {
	private final int threadsCount;
	private int res = 0;

	public Barrier(int threadsCount) {
		this.threadsCount = threadsCount;
	}

	public synchronized void await() {
		if (++res != threadsCount) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		notifyAll();
	}

	public static void main(String[] args) throws InterruptedException {
		final int THREADS_COUNT = 5;
		long startTime = System.currentTimeMillis();
		Barrier barrier = new Barrier(THREADS_COUNT);

		Runnable r = () -> {
			try {
				Thread.sleep((int) (Math.random() * 10000));
			} catch (InterruptedException e) {
				// noop
			}
			System.out.printf("%4d Thread %s arrived to barrier%n", System.currentTimeMillis() - startTime,
					Thread.currentThread().getName());
			barrier.await();
			System.out.printf("%4d Thread %s passed the barrier%n", System.currentTimeMillis() - startTime,
					Thread.currentThread().getName());
		};

		List<Thread> threads = Stream.generate(() -> new Thread(r)).limit(THREADS_COUNT).peek(Thread::start)
				.collect(Collectors.toList());

		for (Thread t : threads) {
			t.join();
		}
	}

}
