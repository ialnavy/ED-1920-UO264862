package p3Arboles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Clase de prueba para AVLTree
 * 
 * @author Ivan Alvarez Lopez - UO264862
 * @version 6.11.2019
 */
class AVLTreeTest {

	Integer[] numbers = { 5, 21, 3, 31, 35, 1, 2, 4, 6, 16 };

	Integer[] nonAddedNumbers = { -1, -31, -56, -23, -83, -9 };

	AVLTree<Integer> tree;

	/**
	 * Metodo principal de prueba
	 */
	@Test
	void test() {
		this.tree = new AVLTree<Integer>();
		System.out.println("Árbol AVL al inicio:");
		System.out.println(this.tree.toString());

		this.tree.setRoot(null);
		testAddNodeProvokingSRR();

		this.tree.setRoot(null);
		testAddNodeProvokingSLR();

		this.tree.setRoot(null);
		testAddNodeProvokingDoubleRotations();

		this.tree.setRoot(null);
		testRemoveNode();

		this.tree.setRoot(null);
		testJavatpointSimulation();

		this.tree.setRoot(null);
		testAddNodeProvokingDoubleRotations();
		testRecorridos();

		this.tree.setRoot(null);
		testTareaDeSeminario();
	}

	/**
	 * Metodo de prueba para hacer la tarea de seminario de AVL
	 */
	@Test
	private void testTareaDeSeminario() {
		System.out.println("-----------------");
		System.out.println("---> Ejercicio 1:");
		System.out.println("-----------------");

		Integer[] numbersToAdd = { 10, 16, 20, 6, 3, 5, 9, 90, 99, 4, 1, 18, 22, 24 };
		this.tree = new AVLTree<Integer>();
		for (Integer number : numbersToAdd) {
			assertNull(this.tree.searchNode(number));
			assertTrue(invariants());

			System.out.println("~~~ Añadiendo el nodo " + number + " ~~~");
			assertEquals(0, this.tree.addNode(number));
			assertTrue(invariants());

			System.out.println("Árbol binario tras añadir el nodo " + number + ":");
			System.out.println(this.tree.toString());

			assertNotNull(this.tree.searchNode(number));
			assertTrue(invariants());
		}

		System.out.println("-----------------");
		System.out.println("---> Ejercicio 2:");
		System.out.println("-----------------");

		Integer[] numbersToRemove = { 6, 18, 20, 16 };
		for (Integer number : numbersToRemove) {
			System.out.println("~~~ Eliminando el nodo " + number + " ~~~");
			assertEquals(0, this.tree.removeNode(number));
			assertTrue(invariants());
			System.out.println(this.tree.toString());
			assertEquals(-1, this.tree.removeNode(number));
			assertTrue(invariants());
		}
	}

	/**
	 * Metodo de prueba para los recorridos
	 */
	@Test
	private void testRecorridos() {
		System.out.println(this.tree.preOrder());
		System.out.println(this.tree.inOrder());
		System.out.println(this.tree.postOrder());
	}

	/**
	 * https://www.javatpoint.com/deletion-in-avl-tree
	 */
	@Test
	private void testJavatpointSimulation() {
		System.out.println("---------------------------");
		System.out.println("TEST DE UN EJERCICIO EN WEB");
		System.out.println("---------------------------");
		System.out.println("===> Case +2 <-> 0:");

		Integer[] numbers = { 5, 10, 15, 20, 30 };

		assertEquals(0, this.tree.addNode(numbers[3]));
		assertEquals(0, this.tree.addNode(numbers[1]));
		assertEquals(0, this.tree.addNode(numbers[4]));
		assertEquals(0, this.tree.addNode(numbers[0]));
		assertEquals(0, this.tree.addNode(numbers[2]));

		System.out.println(this.tree.toString());
		System.out.println("~~~ Eliminando el nodo " + numbers[4] + " ~~~");
		assertEquals(0, this.tree.removeNode(numbers[4]));
		assertEquals(-1, this.tree.removeNode(numbers[4]));
		System.out.println(this.tree.toString());

		System.out.println("~~~ Eliminando el nodo " + numbers[1] + " ~~~");
		assertEquals(0, this.tree.removeNode(numbers[1]));
		assertEquals(-1, this.tree.removeNode(numbers[1]));
		System.out.println(this.tree.toString());

		System.out.println("~~~ Eliminando el nodo " + numbers[3] + " ~~~");
		assertEquals(0, this.tree.removeNode(numbers[3]));
		assertEquals(-1, this.tree.removeNode(numbers[3]));
		System.out.println(this.tree.toString());

		System.out.println("~~~ Eliminando el nodo " + numbers[2] + " ~~~");
		assertEquals(0, this.tree.removeNode(numbers[2]));
		assertEquals(-1, this.tree.removeNode(numbers[2]));
		System.out.println(this.tree.toString());

		System.out.println("~~~ Eliminando el nodo " + numbers[0] + " ~~~");
		assertEquals(0, this.tree.removeNode(numbers[0]));
		assertEquals(-1, this.tree.removeNode(numbers[0]));
		System.out.println(this.tree.toString());

		System.out.println("===> Case -2 <-> 0:");

		Integer[] numbers2 = { 10, 20, 25, 30, 35 };

		assertEquals(0, this.tree.addNode(numbers2[1]));
		assertEquals(0, this.tree.addNode(numbers2[0]));
		assertEquals(0, this.tree.addNode(numbers2[3]));
		assertEquals(0, this.tree.addNode(numbers2[2]));
		assertEquals(0, this.tree.addNode(numbers2[4]));

		System.out.println(this.tree.toString());
		System.out.println("~~~ Eliminando el nodo " + numbers2[0] + " ~~~");
		assertEquals(0, this.tree.removeNode(numbers2[0]));
		assertEquals(-1, this.tree.removeNode(numbers2[0]));
		System.out.println(this.tree.toString());

		System.out.println("~~~ Eliminando el nodo " + numbers2[3] + " ~~~");
		assertEquals(0, this.tree.removeNode(numbers2[3]));
		assertEquals(-1, this.tree.removeNode(numbers2[3]));
		System.out.println(this.tree.toString());

		System.out.println("~~~ Eliminando el nodo " + numbers2[4] + " ~~~");
		assertEquals(0, this.tree.removeNode(numbers2[4]));
		assertEquals(-1, this.tree.removeNode(numbers2[4]));
		System.out.println(this.tree.toString());

		System.out.println("~~~ Eliminando el nodo " + numbers2[2] + " ~~~");
		assertEquals(0, this.tree.removeNode(numbers2[2]));
		assertEquals(-1, this.tree.removeNode(numbers2[2]));
		System.out.println(this.tree.toString());

		System.out.println("~~~ Eliminando el nodo " + numbers2[1] + " ~~~");
		assertEquals(0, this.tree.removeNode(numbers2[1]));
		assertEquals(-1, this.tree.removeNode(numbers2[1]));
		System.out.println(this.tree.toString());
	}

	/**
	 * Metodo de prueba de removeNode()
	 */
	@Test
	private void testRemoveNode() {
		System.out.println("------------------");
		System.out.println("TEST ELIMINACIONES");
		System.out.println("------------------");

		// Intenta meter null
		assertEquals(-2, this.tree.removeNode(null));
		assertTrue(invariants());

		Integer[] numbersSorted = this.numbers.clone();
		Arrays.sort(numbersSorted);
		List<Integer> addedNumbers = new ArrayList<Integer>();

		int i = numbersSorted.length / 2;
		int j = i + 1;
		while (i >= 0 && j < numbersSorted.length) {
			// 'addNode' se da por probado
			this.tree.addNode(numbersSorted[i]);
			this.tree.addNode(numbersSorted[j]);
			addedNumbers.add(numbersSorted[i]);
			addedNumbers.add(numbersSorted[j]);
			i--;
			j++;
		}
		System.out.println("Árbol inicial: ");
		System.out.println(this.tree.toString());
		assertTrue(invariants());

		for (Integer number : numbersSorted) {
			System.out.print("El árbol contiene al nodo " + number + ":");
			if (addedNumbers.contains(number)) {
				System.out.println("Sí");
				System.out.println("~~~ Eliminando el nodo " + number + " ~~~");
				assertEquals(0, this.tree.removeNode(number));
				assertTrue(invariants());
				System.out.println(this.tree.toString());
			} else
				System.out.println("No");
			assertEquals(-1, this.tree.removeNode(number));
			assertTrue(invariants());
		}
	}

	/**
	 * Metodo de prueba provocando rotaciones dobles
	 */
	@Test
	private void testAddNodeProvokingDoubleRotations() {
		System.out.println("----------------------");
		System.out.println("TEST ROTACIONES DOBLES");
		System.out.println("----------------------");

		// Intenta meter null
		assertEquals(-2, this.tree.addNode(null));
		assertTrue(invariants());

		// Se introducen los nodos correctamente
		Integer[] numbersSorted = this.numbers.clone();
		Arrays.sort(numbersSorted);

		int i = 0;
		int j = numbersSorted.length - 1;
		while (i < j) {
			Integer forLeftNumber = numbersSorted[i];
			Integer forRightNumber = numbersSorted[j];

			assertNull(this.tree.searchNode(forLeftNumber));
			assertTrue(invariants());
			assertNull(this.tree.searchNode(forRightNumber));
			assertTrue(invariants());

			System.out.println("~~~ Añadiendo el nodo " + forLeftNumber + " ~~~");
			assertEquals(0, this.tree.addNode(forLeftNumber));
			assertTrue(invariants());

			System.out.println("Árbol binario tras añadir el nodo " + forLeftNumber + ":");
			System.out.println(this.tree.toString());

			System.out.println("~~~ Añadiendo el nodo " + forRightNumber + " ~~~");
			assertEquals(0, this.tree.addNode(forRightNumber));
			assertTrue(invariants());

			System.out.println("Árbol binario tras añadir el nodo " + forRightNumber + ":");
			System.out.println(this.tree.toString());

			assertNotNull(this.tree.searchNode(forLeftNumber));
			assertTrue(invariants());
			assertNotNull(this.tree.searchNode(forRightNumber));
			assertTrue(invariants());

			i++;
			j--;
		}

		System.out.println("Árbol binario tras añadir los nodos:");
		System.out.println(this.tree.toString());
	}

	/**
	 * Metodo de prueba para cuando se añaden nodos cada vez mayores
	 */
	@Test
	private void testAddNodeProvokingSRR() {
		System.out.println("--------------------------------");
		System.out.println("TEST ROTACIONES SIMPLES DERECHAS");
		System.out.println("--------------------------------");

		// Intenta meter null
		assertEquals(-2, this.tree.addNode(null));
		assertTrue(invariants());

		// Se introducen los nodos correctamente
		Integer[] numbersSorted = this.numbers.clone();
		Arrays.sort(numbersSorted);
		for (Integer num : numbersSorted) {
			assertNull(this.tree.searchNode(num));
			assertTrue(invariants());

			System.out.println("~~~ Añadiendo el nodo " + num + " ~~~");
			assertEquals(0, this.tree.addNode(num));
			assertTrue(invariants());

			System.out.println("Árbol binario tras añadir el nodo " + num + ":");
			System.out.println(this.tree.toString());

			assertNotNull(this.tree.searchNode(num));
			assertTrue(invariants());
		}
		System.out.println("Árbol binario tras añadir los nodos:");
		System.out.println(this.tree.toString());

		// No va a insertar nodos ya anadidos
		for (Integer num : this.numbers) {
			assertEquals(-1, this.tree.addNode(num));
			assertTrue(invariants());
		}
		System.out.println("Árbol binario tras intentar volver a añadir los nodos:");
		System.out.println(this.tree.toString());
	}

	/**
	 * Metodo de prueba para cuando se añaden nodos cada vez menores
	 */
	@Test
	private void testAddNodeProvokingSLR() {
		System.out.println("----------------------------------");
		System.out.println("TEST ROTACIONES SIMPLES IZQUIERDAS");
		System.out.println("----------------------------------");

		// Intenta meter null
		assertEquals(-2, this.tree.addNode(null));
		assertTrue(invariants());

		// Se introducen los nodos correctamente
		Integer[] numbersSorted = this.numbers.clone();
		Arrays.sort(numbersSorted, Collections.reverseOrder());
		for (Integer num : numbersSorted) {
			assertNull(this.tree.searchNode(num));
			assertTrue(invariants());

			System.out.println("~~~ Añadiendo el nodo " + num + " ~~~");
			assertEquals(0, this.tree.addNode(num));
			assertTrue(invariants());

			System.out.println("Árbol binario tras añadir el nodo " + num + ":");
			System.out.println(this.tree.toString());

			assertNotNull(this.tree.searchNode(num));
			assertTrue(invariants());
		}
		System.out.println("Árbol binario tras añadir los nodos:");
		System.out.println(this.tree.toString());

		// No va a insertar nodos ya anadidos
		for (Integer num : this.numbers) {
			assertEquals(-1, this.tree.addNode(num));
			assertTrue(invariants());
		}
		System.out.println("Árbol binario tras intentar volver a añadir los nodos:");
		System.out.println(this.tree.toString());
	}

	@Test
	boolean invariants() {
		boolean invariants = true;
		invariants &= invariantBalanceFactor(this.tree.getRoot());
		return invariants;
	}

	@Test
	private boolean invariantBalanceFactor(AVLNode<Integer> node) {
		if (node == null)
			return true;
		return (invariantBalanceFactor(node.getLeft())
				&& ((Math.abs(node.getBF()) == 1) || (Math.abs(node.getBF()) == 0))
				&& invariantBalanceFactor(node.getRight()));
	}
}
