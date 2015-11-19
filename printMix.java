
public class printMix {

	public static void main(String[] args) {
		printNum pn = new printNum();
		printCha pc = new printCha();
		pn.setC(pc);
		pc.setN(pn);
		pn.start();
		pc.start();
	}

}

class printNum extends Thread {
	private printCha c;

	void setC(printCha c) {
		this.c = c;
	}

	public synchronized void run() {
		while (true) {
			for (int i = 1; i < 53; i += 2) {
				System.out.print(i);
				System.out.print(i + 1);
				synchronized (c) {
					c.notify();
				}

				try {

					wait();

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}

class printCha extends Thread {
	private printNum n;

	void setN(printNum n) {
		this.n = n;
	}

	public synchronized void run() {
		while (true) {
			for (char i = 'A'; i <= 'Z'; i++) {
				try {

					wait();

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.print(i);

				synchronized (n) {
					n.notify();
				}
			}
		}
	}
}