package p4Hash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.Test;

class OpenHashTest {

	int size = 5;
	int numElems = 0;
	OpenHashTable<Integer> table = new OpenHashTable<Integer>(size);
	ArrayList<Integer> addedNumbers = new ArrayList<Integer>();

	@Test
	void test() {
		System.out.println("Tabla Hash al inicio:");
		System.out.println(table.toString());
		System.out.println("A?adiendo nodos crecientemente...");

		addTest(null);
		for (int i = 0; i < this.size * 2; i++) {
			addTest(i);
			System.out.println(table.toString());
		}

		System.out.println("Tabla Hash tras ser llenada:");
		System.out.println(table.toString());

		findTest(null);
		for (int i = 0; i < this.size * 2; i++)
			findTest(i);

		System.out.println("Tabla Hash tras hacer b?squedas:");
		System.out.println(table.toString());
		System.out.println("Eliminando nodos decrecientemente...");

		removeTest(null);
		for (int i = this.size * 2; i >= 0; i--) {
			removeTest(i);
			System.out.println(table.toString());
		}

		System.out.println("Tabla Hash tras ser vaciada:");
		System.out.println(table.toString());
		System.out.println("A?adiendo nodos decrecientemente...");

		addTest(null);
		for (int i = this.size * 2; i >= 0; i--) {
			addTest(i);
			System.out.println(table.toString());
		}

		System.out.println("Tabla Hash tras ser llenada:");
		System.out.println(table.toString());

		findTest(null);
		for (int i = 0; i < this.size * 2; i++)
			findTest(i);

		System.out.println("Tabla Hash tras hacer b?squedas:");
		System.out.println(table.toString());
		System.out.println("Eliminando nodos decrecientemente...");

		removeTest(null);
		for (int i = this.size * 2; i >= 0; i--) {
			removeTest(i);
			System.out.println(table.toString());
		}

		System.out.println("Tabla Hash tras ser vaciada:");
		System.out.println(table.toString());
		System.out.println("A?adiendo nodos iterativamente desde ambos lados...");

		addTest(null);
		int i = 0;
		int j = this.size * 2;
		while (j >= 0 && i <= this.size * 2) {
			addTest(i);
			addTest(j);
			System.out.println(table.toString());
			i++;
			j--;
		}

		System.out.println("Tabla Hash tras ser llenada:");
		System.out.println(table.toString());

		Random random = new Random();
		for (int times = Integer.MIN_VALUE; times < Integer.MAX_VALUE; times++)
			this.table.add(random.nextInt());
		System.out.println("Tabla Hash tras un Bogo Test:");
		System.out.println(table.toString());
	}

	void findTest(Integer elem) {
		assertTrue(invariants());
		assertEquals(this.numElems, table.getNumOfElems());
		if (elem == null || !this.addedNumbers.contains(elem))
			assertNull(table.find(elem));
		else {
			assertEquals(elem, this.table.find(elem));
			assertTrue(this.addedNumbers.get(this.addedNumbers.indexOf(elem)) == this.table.find(elem));
		}
		assertEquals(this.numElems, table.getNumOfElems());
		assertTrue(invariants());
	}

	void addTest(Integer elem) {
		assertTrue(invariants());
		assertEquals(this.numElems, table.getNumOfElems());
		if (elem == null) {
			assertEquals(-2, table.add(elem));
			assertEquals(this.numElems, table.getNumOfElems());
//		} else if (this.numElems == this.size) {
//			assertEquals(-1, table.add(elem));
//			assertEquals(this.numElems, table.getNumOfElems());
		} else if (!this.addedNumbers.contains(elem)) {
			assertEquals(0, table.add(elem));
			assertEquals(++this.numElems, table.getNumOfElems());
			this.addedNumbers.add(elem);
		}
		assertTrue(invariants());
	}

	void removeTest(Integer elem) {
		assertTrue(invariants());
		assertEquals(this.numElems, table.getNumOfElems());
		if (elem == null) {
			assertEquals(-2, table.remove(elem));
			assertEquals(this.numElems, table.getNumOfElems());
		} else if (this.addedNumbers.contains(elem)) {
			assertEquals(0, table.remove(elem));
			assertEquals(--this.numElems, table.getNumOfElems());
			this.addedNumbers.remove(elem);
		} else {
			assertEquals(-1, table.remove(elem));
			assertEquals(this.numElems, table.getNumOfElems());
		}
		assertTrue(invariants());
	}

	boolean invariants() {
		return true;
		// (this.table.fc >= this.table.fcDOWN && this.table.fc <= this.table.fcUP);
	}
}
