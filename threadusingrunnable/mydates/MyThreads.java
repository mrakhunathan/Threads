package mydates;

class Runners implements Runnable {
	@Override
	public void run() {
		for (int i = 0; i < 1000; i++) {
			System.out.println(mydates.DataProvider.getDate().toString() +"-"+ i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class MyThreads {
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runners());
		Thread t2 = new Thread(new Runners());

		t1.start();
		t2.start();
	}
}
