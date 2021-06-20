package p3Arboles;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EDBinaryHeapTest {

	EDBinaryHeap<Integer> binaryHeap;

	@Test
	void test() {
		this.binaryHeap = new EDBinaryHeap<Integer>(19);
		addTest();
		getTopTest();
		removeTest();
	}

	void addTest() {
		this.binaryHeap.clear();
		assertTrue(this.binaryHeap.isEmpty());
		assertEquals(-2, this.binaryHeap.add(null));
		assertTrue(this.binaryHeap.isEmpty());
		for (int i = 1; i <= 19; i++) {
			assertEquals(0, this.binaryHeap.add(i));
			assertFalse(this.binaryHeap.isEmpty());
		}
		assertEquals(-2, this.binaryHeap.add(null));
		assertFalse(this.binaryHeap.isEmpty());
		assertEquals(-1, this.binaryHeap.add(0));
		assertFalse(this.binaryHeap.isEmpty());
		assertEquals(-1, this.binaryHeap.add(-3));
		assertFalse(this.binaryHeap.isEmpty());
	}

	void getTopTest() {
		addTest();
		for (int i = 1; i <= 19; i++) {
			assertFalse(this.binaryHeap.isEmpty());
			assertEquals(i, this.binaryHeap.getTop());
		}
		assertTrue(this.binaryHeap.isEmpty());
		assertNull(this.binaryHeap.getTop());
		assertTrue(this.binaryHeap.isEmpty());
	}

	void removeTest() {
		addTest();
		assertFalse(this.binaryHeap.isEmpty());
		assertEquals(-2, this.binaryHeap.remove(null));
		assertFalse(this.binaryHeap.isEmpty());
		assertEquals(-1, this.binaryHeap.remove(-3));
		assertFalse(this.binaryHeap.isEmpty());
		for (int i = 1; i <= 19; i++) {
			assertFalse(this.binaryHeap.isEmpty());
			assertEquals(0, this.binaryHeap.remove(i));
			assertEquals(-1, this.binaryHeap.remove(i));
		}
		assertTrue(this.binaryHeap.isEmpty());

		addTest();
		assertFalse(this.binaryHeap.isEmpty());
		assertEquals(0, this.binaryHeap.remove(5));
		assertEquals(-1, this.binaryHeap.remove(5));

		assertFalse(this.binaryHeap.isEmpty());

		assertEquals(0, this.binaryHeap.remove(10));
		assertEquals(-1, this.binaryHeap.remove(10));

		assertFalse(this.binaryHeap.isEmpty());

		assertEquals(0, this.binaryHeap.remove(11));
		assertEquals(-1, this.binaryHeap.remove(11));

		assertFalse(this.binaryHeap.isEmpty());

		assertEquals(0, this.binaryHeap.remove(6));
		assertEquals(-1, this.binaryHeap.remove(6));

		assertFalse(this.binaryHeap.isEmpty());

		assertEquals(0, this.binaryHeap.remove(7));
		assertEquals(-1, this.binaryHeap.remove(7));

		assertFalse(this.binaryHeap.isEmpty());

		assertEquals(0, this.binaryHeap.remove(2));
		assertEquals(-1, this.binaryHeap.remove(2));

		assertFalse(this.binaryHeap.isEmpty());

		assertEquals(0, this.binaryHeap.remove(18));
		assertEquals(-1, this.binaryHeap.remove(18));

		assertFalse(this.binaryHeap.isEmpty());

		assertEquals(0, this.binaryHeap.remove(4));
		assertEquals(-1, this.binaryHeap.remove(4));

		assertFalse(this.binaryHeap.isEmpty());

		assertEquals(0, this.binaryHeap.remove(1));
		assertEquals(-1, this.binaryHeap.remove(1));

		assertFalse(this.binaryHeap.isEmpty());

		assertEquals(0, this.binaryHeap.remove(3));
		assertEquals(-1, this.binaryHeap.remove(3));

		assertFalse(this.binaryHeap.isEmpty());

		assertEquals(0, this.binaryHeap.remove(8));
		assertEquals(-1, this.binaryHeap.remove(8));

		assertFalse(this.binaryHeap.isEmpty());

		assertEquals(0, this.binaryHeap.remove(19));
		assertEquals(-1, this.binaryHeap.remove(19));

		assertFalse(this.binaryHeap.isEmpty());

		assertEquals(0, this.binaryHeap.remove(9));
		assertEquals(-1, this.binaryHeap.remove(9));

		assertFalse(this.binaryHeap.isEmpty());

		assertEquals(0, this.binaryHeap.remove(14));
		assertEquals(-1, this.binaryHeap.remove(14));

		assertFalse(this.binaryHeap.isEmpty());

		assertEquals(0, this.binaryHeap.remove(16));
		assertEquals(-1, this.binaryHeap.remove(16));

		assertFalse(this.binaryHeap.isEmpty());

		assertEquals(0, this.binaryHeap.remove(12));
		assertEquals(-1, this.binaryHeap.remove(12));

		assertFalse(this.binaryHeap.isEmpty());

		assertEquals(0, this.binaryHeap.remove(13));
		assertEquals(-1, this.binaryHeap.remove(13));

		assertFalse(this.binaryHeap.isEmpty());

		assertEquals(0, this.binaryHeap.remove(15));
		assertEquals(-1, this.binaryHeap.remove(15));

		assertFalse(this.binaryHeap.isEmpty());

		assertEquals(0, this.binaryHeap.remove(17));
		assertEquals(-1, this.binaryHeap.remove(17));

		assertTrue(this.binaryHeap.isEmpty());
	}
}
