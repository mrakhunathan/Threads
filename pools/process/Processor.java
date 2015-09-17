package process;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Processor implements Runnable {
	int id;

	public Processor(int id) {
		this.id = id;
	}

	public static long getCurrentMemory() {
		System.gc();
		return (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
	}

	@Override
	public void run() {
		System.out.println("Starting Process ..." + id);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Completed Process ..." + id);
	}

	public static void main(String[] args) throws InterruptedException {
		Date start = new Date();
		long beforeExecutor = getCurrentMemory();
		ExecutorService executor = Executors.newFixedThreadPool(1);
		long afterExecutor = getCurrentMemory();
		// Size of Pool Matters on execution time and memory
		for (int i = 0; i < 105; i++) {
			executor.submit(new Processor(i));
		}
		executor.shutdown();
		System.out.println("All Tasks Submitted....");
		executor.awaitTermination(1, TimeUnit.DAYS);
		System.out.println("All Tasks Completed....");
		long afterProcess = getCurrentMemory();
		Date end = new Date();
		
		
		System.out.println("Total Time Taken = " + (end.getTime() - start.getTime()));
		System.out.println("beforeExecutor=" + beforeExecutor);
		System.out.println("beforeExecutor=" + afterExecutor);
		System.out.println("Difference=" + (afterExecutor-beforeExecutor));
		System.out.println("afterProcess=" + afterProcess);
		System.out.println("Difference=" + (afterProcess-afterExecutor));
		
	}
}
