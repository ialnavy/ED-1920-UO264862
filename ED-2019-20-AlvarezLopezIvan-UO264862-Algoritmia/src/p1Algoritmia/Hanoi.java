package p1Algoritmia;

import java.util.Stack;

public class Hanoi {
	private Stack<Integer> origin;
	private Stack<Integer> destination;
	private Stack<Integer> auxiliar;
	private int n;

	public Hanoi(int n) {
		this.n = n;
		this.origin = new Stack<Integer>();
		this.destination = new Stack<Integer>();
		this.auxiliar = new Stack<Integer>();
		for (int i = n; i > 0; i--)
			this.origin.push(Integer.valueOf(i));
		System.out.println("Hanoi towers have been created.");
		printStatus();
	}

	public void run() {
		System.out.println("Starting Hanoi towers algorithm...");
		hanoi(this.n, this.origin, this.destination, this.auxiliar);
	}

	private void hanoi(int n, Stack<Integer> origin, Stack<Integer> destination, Stack<Integer> auxiliar) {
		if (n == 1) {
			destination.push(origin.pop());
			System.out.println("A movement has been made:");
			printStatus();
		} else {
			hanoi(n - 1, origin, auxiliar, destination);
			destination.push(origin.pop());
			System.out.println("A movement has been made:");
			printStatus();
			hanoi(n - 1, auxiliar, destination, origin);
		}
	}

	public void printStatus() {
		System.out.println("Tower origin: " + this.origin.toString());
		System.out.println("Tower auxiliar: " + this.auxiliar.toString());
		System.out.println("Tower destination: " + this.destination.toString());
	}
}
