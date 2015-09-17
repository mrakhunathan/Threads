package have;

class Runners extends Thread {
	public void run() {
		for (int i = 0; i < 1000; i++) {
			System.out.println("Hi.."+i);
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
		Runners r = new Runners();
		r.start();
		Runners r2 = new Runners();
		r2.start();
	}
}
