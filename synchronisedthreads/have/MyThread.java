package have;

public class MyThread implements Runnable {
	static int count = 0;

	public static synchronized void increment() {
		count++;
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			increment();
			System.out.println("i val="+i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {

		Thread t1 = new Thread(new MyThread());
		Thread t2 = new Thread(new MyThread());
		t1.start();
		t2.start();

		try {
			System.out.println("Executing t1...");
			t1.join();
			System.out.println("Executing t2...");
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Count=" + count);
	}
}