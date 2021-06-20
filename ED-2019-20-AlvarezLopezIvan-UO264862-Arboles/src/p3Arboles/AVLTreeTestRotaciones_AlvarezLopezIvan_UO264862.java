package p3Arboles;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import evalNestor.EvalAVLTree;

/**
 * Clase AVLTreeTestRotaciones que prueba todas las rotaciones posibles
 * 
 * @author Ivan Alvarez Lopez - UO264862
 * @version 13.11.2019
 */
public class AVLTreeTestRotaciones_AlvarezLopezIvan_UO264862 {

	EvalAVLTree<Double> b = new EvalAVLTree<Double>();

	// Son un ejemplo, tienes que sustituirlos por los que te produzcan a ti las
	// rotaciones pedidas la d detras del numero lo convierte en double
	Double[] nodosQueHacenRotar = new Double[] { 13.0, 6.5, 9.0, 5.5, 6.5, 5.25, 8.0, 5.5, 9.0, 5.75, -3.0 };

	List<Double> todosLosNodos = new ArrayList<Double>();

	/**
	 * Crea un arbol inicial introduciendole nodos
	 */
	public void initialice() {
		todosLosNodos.add(5.0);
		todosLosNodos.add(9.0);
		todosLosNodos.add(1.0);
		todosLosNodos.add(-3.0);
		todosLosNodos.add(3.0);
		todosLosNodos.add(7.0);
		todosLosNodos.add(13.0);
		todosLosNodos.add(14.0);
		todosLosNodos.add(11.0);
		todosLosNodos.add(8.0);
		todosLosNodos.add(6.0);
		todosLosNodos.add(4.0);
		todosLosNodos.add(2.0);
		todosLosNodos.add(-1.0);
		todosLosNodos.add(-4.0);
		for (Double d : todosLosNodos)
			assertEquals(0, b.addNode(d));
		// System.out.println("Árbol inicial: ");
		// System.out.println(b.toString());
	}

	/**
	 * Metodo de prueba
	 */
	@Test
	// @Before
	public void test0() {
		initialice();

		removeTest(14.0);
		removeTest(11.0);
		removeTest(6.0);
		removeTest(13.0);
		// 1. DLR(9.0) -> SRR(7.0), SLR(9.0)

		addTest(6.0);
		addTest(6.5);
		// 2. DLR(7.0) -> SRR(6.0), SLR(7.0)

		removeTest(9.0);
		// 3. SLR(8.0)

		removeTest(7.0);
		removeTest(8.0);
		addTest(5.5);
		// 4. SLR(6.5)

		addTest(5.25);
		removeTest(6.5);
		// 5. SLR(6.0)

		addTest(5.75);
		removeTest(5.25);
		// 6. DRR(5.5) -> SLR(6.0), SRR(5.5)

		addTest(7.0);
		addTest(8.0);
		// 7. SRR(6.0)

		removeTest(6.0);
		removeTest(5.5);
		// 8. SRR(5.75)

		addTest(10.0);
		addTest(9.0);
		// 9. DRR(8.0) -> SLR(10.0), SRR(8.0)

		removeTest(5.75);
		// 10. SRR(7.0)

		addTest(6.0);
		addTest(15.0);
		addTest(9.5);

		addTest(-8.0);
		addTest(-3.5);
		addTest(-2.0);
		addTest(0.5);
		addTest(1.5);
		addTest(2.5);
		addTest(3.5);
		addTest(4.5);
		addTest(5.5);
		addTest(6.5);
		addTest(7.5);
		addTest(8.5);
		addTest(9.25);
		addTest(9.75);
		addTest(13.0);
		addTest(20.0);

		removeTest(4.5);
		removeTest(3.5);
		removeTest(2.5);
		removeTest(0.5);
		removeTest(-2.0);
		removeTest(-3.5);
		removeTest(-8.0);

		removeTest(-4.0);
		removeTest(3.0);
		removeTest(1.5);
		removeTest(1.0);

		removeTest(-3.0);
		// 11. SRR(-1.0), SRR(5.0)
	}

	/**
	 * Prueba la adiccion de un nodo al arbol
	 * 
	 * @param a Informacion del nodo a introducir
	 */
	public void addTest(Double a) {
		invariantNineNodesOrMore();
		if (!todosLosNodos.contains(a.doubleValue()))
			todosLosNodos.add(a.doubleValue());
		assertEquals(0, b.addNode(a.doubleValue()));
		invariantNineNodesOrMore();
	}

	/**
	 * Prueba la eliminacion de un nodo al arbol
	 * 
	 * @param a Informacion del nodo a eliminar
	 */
	public void removeTest(Double a) {
		invariantNineNodesOrMore();
		if (!todosLosNodos.contains(a.doubleValue()))
			todosLosNodos.add(a.doubleValue());
		assertEquals(0, b.removeNode(a.doubleValue()));
		invariantNineNodesOrMore();
	}

	/**
	 * Verifica que la cantidad de nodos sea 9 o mayor
	 */
	public void invariantNineNodesOrMore() {
		int counter = 0;
		for (Double d : todosLosNodos)
			if (b.searchNode(d) != null)
				counter++;
		assertTrue(counter >= 9);
	}
}