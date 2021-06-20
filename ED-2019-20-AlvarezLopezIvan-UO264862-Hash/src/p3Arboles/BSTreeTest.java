package p3Arboles;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.Test;

/**
 * Clase de pruebas para las clases BSTree y BSTNode
 * 
 * @author Ivan Alvarez Lopez UO264862
 * @version 23.10.2019
 */
class BSTreeTest {

	Integer[] numbers = { 5, 21, 3, 31, 35, 1, 2, 4, 6, 16 };

	Integer[] nonAddedNumbers = { -1, -31, -56, -23, -83, -9 };

	BSTree<Integer> tree;

	@Test
	void test() {
		this.tree = new BSTree<Integer>();
		System.out.println("Árbol binario al inicio:");
		System.out.println(this.tree.toString());
		testAddNode();
		testPaths();
		testSearch();
		testRemove();
		testHeight();
		testAPE();
	}

	@Test
	void testAPE() {
		System.out.println("PRUEBA DE APE");
		System.out.println("~~~ Test 1: ~~~");
		this.tree = new BSTree<Integer>();
		assertTrue(this.tree.isAPE());
		assertEquals(0, this.tree.addNode(0));
		assertTrue(this.tree.isAPE());
		assertEquals(0, this.tree.addNode(1));
		assertTrue(this.tree.isAPE());
		assertEquals(0, this.tree.addNode(-1));
		assertTrue(this.tree.isAPE());
		assertEquals(0, this.tree.addNode(2));
		assertTrue(this.tree.isAPE());
		assertEquals(0, this.tree.addNode(-2));
		for (int i = 3; i < 10; i++) {
			System.out.println(this.tree.toString());
			assertEquals(0, this.tree.addNode(i));
			assertFalse(this.tree.isAPE());
			System.out.println(this.tree.toString());
			assertEquals(0, this.tree.addNode(-i));
			assertFalse(this.tree.isAPE());
		}

		System.out.println("~~~ Test 2: ~~~");
		this.tree = new BSTree<Integer>();
		int a = -100;
		int b = 100;
		int i = 0;

		assertTrue(this.tree.isAPE());
		assertEquals(0, this.tree.addNode(i));
		assertTrue(this.tree.isAPE());
		i++;
		System.out.println(this.tree.toString());

		for (int k = 0; k < 2; k++) {
			assertTrue(this.tree.isAPE());
			assertEquals(0, this.tree.addNode(a / i));
			System.out.println(this.tree.toString());
			assertTrue(this.tree.isAPE());
			assertEquals(0, this.tree.addNode(b / i));
			System.out.println(this.tree.toString());
			assertTrue(this.tree.isAPE());
			i++;
		}

		for (int k = 0; k < 11; k++) {
			assertEquals(0, this.tree.addNode(a / i));
			System.out.println(this.tree.toString());
			assertFalse(this.tree.isAPE());
			assertEquals(0, this.tree.addNode(b / i));
			System.out.println(this.tree.toString());
			assertFalse(this.tree.isAPE());
			i++;
		}
		assertEquals(-1, this.tree.addNode(a / i));
	}

	@Test
	void testHeight() {
		System.out.println("PRUEBA DE ALTURA");

		System.out.println("~~~ Añadido del 0 al 9 inclusives ~~~");

		for (int k = 0; k < 10; k++) {
			assertEquals(0, this.tree.addNode(k));
			assertEquals(-1, this.tree.addNode(k));
			System.out.println(this.tree.toString());
			System.out.println("Height: " + this.tree.getHeight());
		}

		System.out.println("~~~ Eliminado del 0 al 9 inclusives ~~~");

		for (int k = 0; k < 10; k++) {
			assertEquals(0, this.tree.removeNode(k));
			assertEquals(-1, this.tree.removeNode(k));
			System.out.println(this.tree.toString());
			System.out.println("Height: " + this.tree.getHeight());
		}

		System.out.println("~~~ Añadido del -9 al 9 inclusives desde el centro ~~~");

		int i = 0;
		int j = -1;
		while (j > -10) {
			i++;
			assertEquals(0, this.tree.addNode(i));
			assertEquals(0, this.tree.addNode(j));
			assertEquals(-1, this.tree.addNode(i));
			assertEquals(-1, this.tree.addNode(j));
			System.out.println(this.tree.toString());
			System.out.println("Height: " + this.tree.getHeight());
			j--;
		}

		System.out.println("~~~ Eliminado del -9 al 9 inclusives desde el centro ~~~");

		i = 0;
		j = -1;
		while (j > -10) {
			i++;
			assertEquals(0, this.tree.removeNode(i));
			assertEquals(0, this.tree.removeNode(j));
			assertEquals(-1, this.tree.removeNode(i));
			assertEquals(-1, this.tree.removeNode(j));
			System.out.println(this.tree.toString());
			System.out.println("Height: " + this.tree.getHeight());
			j--;
		}

		System.out.println("~~~ Bogo Test ~~~");

		Random random = new Random();
		for (int times = 0; times < 1000; times++)
			this.tree.addNode(random.nextInt());
		System.out.println(this.tree.toString());
		System.out.println("Height: " + this.tree.getHeight());
	}

	@Test
	void testRemove() {
		// No hay nodos null, no los pretendas borrar
		assertEquals(-2, this.tree.removeNode(null));

		// No borra lo que no tiene que borrar
		for (Integer num : this.nonAddedNumbers)
			assertEquals(-1, this.tree.removeNode(num));

		// Borra lo que tiene que borrar
		for (Integer num : this.numbers) {
			assertEquals(0, this.tree.removeNode(num));
			System.out.println("Nodo '" + num + "' borrado:");
			System.out.println(this.tree.toString());
		}

		// No borra lo que no tiene que borrar
		for (Integer num : this.nonAddedNumbers)
			assertEquals(-1, this.tree.removeNode(num));
		for (Integer num : this.numbers)
			assertEquals(-1, this.tree.removeNode(num));
	}

	@Test
	void testSearch() {
		// No hay nodos null, no los intentes buscar
		assertNull(this.tree.searchNode(null));

		// Hay algun nodo que contiene algo que es igual
		for (int i = 0; i < numbers.length; i++) {
			boolean condition = false;
			for (Integer num : this.numbers)
				condition |= (num.equals(this.tree.searchNode(num)));
			assertTrue(condition);
		}

		// Hay algun nodo que contiene a ese preciso objeto
		for (int i = 0; i < numbers.length; i++) {
			boolean condition = false;
			for (Integer num : this.numbers)
				condition |= (num == this.tree.searchNode(num));
			assertTrue(condition);
		}

		// No hay ningun nodo que contenga algo que sea igual
		for (int i = 0; i < nonAddedNumbers.length; i++) {
			boolean condition = false;
			for (Integer num : this.nonAddedNumbers)
				condition |= (num.equals(this.tree.searchNode(num)));
			assertFalse(condition);
		}

		// No hay ningun nodo que contenga a ese preciso objeto
		for (int i = 0; i < nonAddedNumbers.length; i++) {
			boolean condition = false;
			for (Integer num : this.nonAddedNumbers)
				condition |= (num == this.tree.searchNode(num));
			assertFalse(condition);
		}
	}

	@Test
	void testPaths() {
		System.out.println("Recorrido preorden:");
		System.out.println(this.tree.preOrder());

		System.out.println("Recorrido postorden:");
		System.out.println(this.tree.postOrder());

		System.out.println("Recorrido inorden:");
		System.out.println(this.tree.inOrder());
	}

	@Test
	void testAddNode() {
		// Intenta meter null
		assertEquals(-2, this.tree.addNode(null));

		// Se introducen los nodos correctamente
		for (Integer num : this.numbers)
			assertEquals(0, this.tree.addNode(num));

		System.out.println("Árbol binario tras añadir los nodos:");
		System.out.println(this.tree.toString());

		// No va a insertar nodos ya anadidos
		for (Integer num : this.numbers)
			assertEquals(-1, this.tree.addNode(num));
		System.out.println("Árbol binario tras intentar volver a añadir los nodos:");
		System.out.println(this.tree.toString());
	}

}
