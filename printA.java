
public class printA extends Thread {

	private printB B;
	void sets(printB B) {
		this.B = B;
	}

	public synchronized void run() {
		while (true) {
			synchronized (this) {
				System.out.println("A");
				synchronized (B) {
					B.notify();
				}
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) {
		printA pa = new printA();
		printB pb = new printB();
		printC pc = new printC();
		printD pd = new printD();
		printE pe = new printE();
		pa.sets(pb);
		pb.sets(pc);
		pc.sets(pd);
		pd.sets(pe);
		pe.sets(pa);

		pa.start();
		pb.start();
		pc.start();
		pd.start();
		pe.start();
	}
}

class printB extends Thread {
	private printC C;

	void sets(printC C) {
		this.C = C;
	}

	public synchronized void run() {
		while (true) {
			synchronized (this) {

				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("B");
				synchronized (C) {
					C.notify();
				}
			}
		}
	}
}

class printC extends Thread {
	private printD D;

	void sets(printD D) {
		this.D = D;
	}

	public synchronized void run() {
		while (true) {
			synchronized (this) {

				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("C");
				synchronized (D) {
					D.notify();
				}
			}
		}
	}
}

class printD extends Thread {
	private printE E;

	void sets(printE E) {
		this.E = E;
	}

	public synchronized void run() {
		while (true) {
			synchronized (this) {

				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("D");
				synchronized (E) {
					E.notify();
				}
			}
		}
	}
}

class printE extends Thread {
	private printA A;

	void sets(printA A) {
		this.A = A;
	}

	public synchronized void run() {
		while (true) {
			synchronized (this) {

				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("E");
				synchronized (A) {
					A.notify();
				}
			}
		}
	}
}
