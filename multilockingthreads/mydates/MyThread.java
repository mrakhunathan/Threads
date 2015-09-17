package mydates;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyThread {
	List<Integer> l1 = new ArrayList<Integer>();
	List<Integer> l2 = new ArrayList<Integer>();
	Random random = new Random();
	Object lock1 = new Object();
	Object lock2 = new Object();

	public void stage1() throws InterruptedException {
		synchronized (l1) {
			Thread.sleep(1);
			l1.add(random.nextInt(1000));
		}
	}

	public void stage2() throws InterruptedException {
		synchronized (l2) {
			Thread.sleep(1);
			l2.add(random.nextInt(1000));
		}
	}

	public void process() throws InterruptedException {
		for (int i = 0; i < 100; i++) {
			stage1();
			stage2();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		MyThread obj = new MyThread();
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					obj.process();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					obj.process();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		long start = System.currentTimeMillis();
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		long end = System.currentTimeMillis();
		System.out.println("Time Taken = " + (end - start));
		System.out.println("List1 Size = " + obj.l1.size() + "..List2 Size = " + obj.l2.size());
		System.out.println(obj.l1.toString());
	}
}