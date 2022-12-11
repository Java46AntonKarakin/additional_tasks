package telran.guarded_blocks.tasks;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Semaphore {
	private int resourceCount;

	public Semaphore(int resourceCount) {
		this.resourceCount = resourceCount;
	}

	public synchronized void acquire() throws InterruptedException {
		while (resourceCount == 0) {
			wait();
		}
		resourceCount--;
	}

	public synchronized void release() {
		resourceCount++;
		notify();
	}

	public static void main(String[] args) throws InterruptedException {
		final int RESOURCE_COUNT = 5;
		final int THREADS_COUNT = 10;
		final int PER_THREAD_ACQUISITIONS_COUNT = 3; // number "acquire" operations performed by each thread
		AtomicInteger acquiredResources = new AtomicInteger(0);

		Semaphore sem = new Semaphore(RESOURCE_COUNT);

		Runnable r = () -> {
			try {
				for (int i = 0; i < PER_THREAD_ACQUISITIONS_COUNT; i++) {
					System.out.printf("Thread %s needs resource%n", Thread.currentThread().getName());

					sem.acquire();

					int newCount = acquiredResources.incrementAndGet();

					System.out.printf("Thread %s acquired, tot acquired: %d %n", Thread.currentThread().getName(),
							newCount);

					Thread.sleep((int) (Math.random() * 2000));

					newCount = acquiredResources.decrementAndGet(); // before release to avoid racing

					sem.release();

					System.out.printf("Thread %s released, tot acquired: %d %n", Thread.currentThread().getName(),
							newCount);
				}
			} catch (InterruptedException e) {
				// noop;
			}
		};

		int[] thrName = { 0 };
		List<Thread> threads = Stream.generate(() -> new Thread(r, "" + thrName[0]++)).limit(THREADS_COUNT)
				.peek(Thread::start).collect(Collectors.toList());

		for (Thread t : threads) {
			t.join();
		}
	}

}
