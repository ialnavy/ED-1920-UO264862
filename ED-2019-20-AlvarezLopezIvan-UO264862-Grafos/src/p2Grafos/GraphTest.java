package p2Grafos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.Stack;

import org.junit.jupiter.api.Test;

/**
 * Clase de pruebas para la clase Graph.
 * 
 * @author Ivan Alvarez Lopez - UO264862
 * @version 17.10.2019
 */
class GraphTest {

    private Graph<Character> graphCharacters;
    private int graphSize;
    // Nodos comprendidos en las pruebas
    private final static Character[] chars = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
	    'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    // Nodos que no se van a meter a ningun grafo en las pruebas
    private final static Character[] evilChars = { '!', '@', '#', '$', '%', '^', '&', '*', '(', ')' };

    /**
     * Metodo principal de pruebas.
     */
    @Test
    void test() {
	// El tamano del grafo va a ser la mitad del tamano del vector de caracteres con
	// los que vamos a hacer pruebas
	this.graphSize = chars.length / 2;
	this.graphCharacters = new Graph<Character>(this.graphSize);

	testAddNode();

	testAddGetEdge();

	testRemoveEdge();

	testRemoveNode();

	testDijkstra();

	testPath();

	testFloyd();

	testDepthPath();
    }

    /**
     * Selecciona que grafo inicializar
     */
    @Test
    private void inicializarGrafo() {
	inicializarGrafoDeClase();
	// inicializarBogoGraph();
    }

    /**
     * Test Depth Path algorithm.
     */
    @Test
    private void testDepthPath() {
	inicializarGrafo();
	System.out.println("Grafo escrito en la pizarra de clase:");
	System.out.println(this.graphCharacters.toString());
	System.out.println("Testeando el algoritmo de Recorrido en Profundidad...");
	for (Character character : chars) {
	    System.out.println("Recorrido en profundidad desde el nodo " + character.toString() + ":");
	    System.out.println(this.graphCharacters.recorridoProfundidad(character));
	}
    }

    /**
     * Test Path algorithm.
     */
    @Test
    private void testPath() {
	inicializarGrafo();
	System.out.println("Grafo escrito en la pizarra de clase:");
	System.out.println(this.graphCharacters.toString());
	System.out.println("Testeando el algoritmo Path...");
	for (int i = 0; i < chars.length; i++) {
	    for (int j = 0; j < chars.length; j++) {
		Character nodeOrigin = chars[i];
		Character nodeDestination = chars[j];
		System.out.println("Camino entre el nodo " + nodeOrigin.toString() + " y el nodo "
			+ nodeDestination.toString() + ":");
		String path = this.graphCharacters.path(nodeOrigin, nodeDestination);
		System.out.println((path.length() == 0) ? "Algún nodo no existe" : path);
	    }
	}
    }

    /**
     * Test Floyd algorithm.
     */
    @Test
    private void testFloyd() {
	inicializarGrafo();
	System.out.println("Grafo escrito en la pizarra de clase:");
	System.out.println(this.graphCharacters.toString());
	System.out.println("Testeando el algoritmo de Floyd...");
	this.graphCharacters.floyd();
	System.out.println("Matriz A:");
	for (int i = 0; i < this.graphCharacters.numNodes; i++) {
	    for (int j = 0; j < this.graphCharacters.numNodes; j++) {
		System.out.print(this.graphCharacters.aFloyd[i][j] + "\t");
	    }
	    System.out.println();
	}
	System.out.println("Matriz P:");
	for (int i = 0; i < this.graphCharacters.numNodes; i++) {
	    for (int j = 0; j < this.graphCharacters.numNodes; j++) {
		System.out.print(this.graphCharacters.pFloyd[i][j] + "\t");
	    }
	    System.out.println();
	}
    }

    /**
     * Test Dijkstra algorithm.
     */
    @Test
    private void testDijkstra() {
	inicializarGrafo();
	System.out.println("Grafo escrito en la pizarra de clase:");
	System.out.println(this.graphCharacters.toString());
	System.out.println("Testeando el algoritmo de Dijkstra...");
	// Resultado esperado
	assertArrayEquals(new double[] { 0.0, 10.0, 20.0, 23.0, 32.0, 58.0 }, this.graphCharacters.dijkstra('A'));
    }

    /**
     * Inicializa el grafo que hizo el profesor en la pizarra en clase
     */
    @Test
    private void inicializarGrafoDeClase() {
	this.graphCharacters = new Graph<Character>(6);

	assertEquals(0, this.graphCharacters.addNode('A'));
	assertEquals(0, this.graphCharacters.addNode('B'));
	assertEquals(0, this.graphCharacters.addNode('C'));
	assertEquals(0, this.graphCharacters.addNode('D'));
	assertEquals(0, this.graphCharacters.addNode('E'));
	assertEquals(0, this.graphCharacters.addNode('F'));

	assertEquals(0, this.graphCharacters.addEdge('A', 'B', 10));
	assertEquals(0, this.graphCharacters.addEdge('A', 'C', 20));
	assertEquals(0, this.graphCharacters.addEdge('A', 'D', 30));
	assertEquals(0, this.graphCharacters.addEdge('A', 'E', 40));
	assertEquals(0, this.graphCharacters.addEdge('A', 'F', 100));
	assertEquals(0, this.graphCharacters.addEdge('B', 'D', 13));
	assertEquals(0, this.graphCharacters.addEdge('C', 'E', 12));
	assertEquals(0, this.graphCharacters.addEdge('D', 'F', 35));
    }

    /**
     * BOGO GRAPH ┌( ಠ_ಠ )┘
     */
    @Test
    private void inicializarBogoGraph() {
	Random random = new Random();
	int size = random.nextInt(chars.length - 1) + 1;

	this.graphCharacters = new Graph<Character>(size);

	Stack<Character> pila = new Stack<Character>();

	int i, j;
	i = random.nextInt(chars.length);
	while (i < size) {
	    Character nextChar = chars[i];
	    this.graphCharacters.addNode(nextChar);
	    pila.push(nextChar);
	    i = (i + 1 >= size) ? 0 : i + 1;
	}

	i = 0;
	while (i < size) {
	    if (pila.size() == 0)
		break;

	    Character nextCharOrigin = pila.pop();
	    @SuppressWarnings("unchecked")
	    Stack<Character> pilaRespaldo = (Stack<Character>) pila.clone();
	    j = (i == 0) ? 1 : 0;
	    while (j < size) {
		Character nextCharDestination = pila.pop();
		int done = this.graphCharacters.addEdge(nextCharOrigin, nextCharDestination, random.nextInt(99) + 1);
		if (done == 0)
		    j += (j + 1 == i) ? 2 : 1;
	    }
	    pila = pilaRespaldo;
	    i++;
	}
    }

    /**
     * Test node sustraction. DO NOT EXECUTE WITHOUT INITIALICE THE GRAPH!
     */
    @Test
    private void testRemoveNode() {
	// 'null' no es ningun nodo
	assertEquals(-4, this.graphCharacters.removeNode(null));

	// No se pueden eliminar nodos que no existen
	for (Character nextChar : evilChars)
	    assertEquals(-1, this.graphCharacters.removeNode(nextChar));

	// El grafo va a pasar a ser un grafo completo
	Random random = new Random();
	double[][] generatedWeights = new double[this.graphSize][this.graphSize];

	for (int i = 0; i < this.graphSize; i++) {
	    for (int j = 0; j < this.graphSize; j++) {
		double nextWeight = random.nextDouble() + 1;
		generatedWeights[i][j] = nextWeight;
		this.graphCharacters.addEdge(chars[i], chars[j], nextWeight);
	    }
	}

	System.out.println("--- EL GRAFO DEBERIA ESTAR LLENO DE ARISTAS:");
	System.out.println(this.graphCharacters.toString());
	System.out.println("---");

	Stack<Character> graphNodes = new Stack<Character>();
	graphNodes.push('E');
	graphNodes.push('G');
	graphNodes.push('C');
	graphNodes.push('A');
	graphNodes.push('F');
	graphNodes.push('B');
	graphNodes.push('D');
	graphNodes.push('H');
	while (!graphNodes.isEmpty()) {
	    Character removedNode = graphNodes.pop();
	    assertEquals(0, this.graphCharacters.removeNode(removedNode));
	    assertEquals(-1, this.graphCharacters.removeNode(removedNode));
	    System.out.println("--- EL GRAFO TRAS BORRAR EL NODO '" + removedNode + "':");
	    System.out.println(this.graphCharacters.toString());
	    System.out.println("---");
	}
    }

    /**
     * Test edge sustraction. Dependent of 'testAddGetEdge()'. DO NOT EXECUTE
     * WITHOUT INITIALICE THE GRAPH!
     */
    @Test
    private void testRemoveEdge() {
	System.out.println(
		"--- EL GRAFO DEBERIA ESTAR LLENO (De la " + chars[0] + " a la " + chars[(this.graphSize - 1)] + "):");
	System.out.println(this.graphCharacters.toString());
	System.out.println("---");

	Character problematicChar1 = '@';
	Character problematicChar2 = '#';
	Character nextChar = chars[0];
	// El primer nodo no existe
	assertEquals(-1, this.graphCharacters.removeEdge(problematicChar1, nextChar));
	assertEquals(-1, this.graphCharacters.removeEdge(problematicChar2, nextChar));

	// El segundo nodo no existe
	assertEquals(-2, this.graphCharacters.removeEdge(nextChar, problematicChar1));
	assertEquals(-2, this.graphCharacters.removeEdge(nextChar, problematicChar2));

	// Ambos nodos no existen en el grafo
	assertEquals(-3, this.graphCharacters.removeEdge(problematicChar2, problematicChar1));
	assertEquals(-3, this.graphCharacters.removeEdge(problematicChar1, problematicChar2));
	assertEquals(-3, this.graphCharacters.removeEdge(problematicChar1, problematicChar1));
	assertEquals(-3, this.graphCharacters.removeEdge(problematicChar2, problematicChar2));

	// Eliminamos las aristas del grafo
	for (int i = 0; i < this.graphSize; i++) {
	    for (int j = 0; j < this.graphSize; j++) {
		// Se elimina el arista correctamente
		assertEquals(0, this.graphCharacters.removeEdge(chars[i], chars[j]));
		// Si lo volvemos a intentar, como ya se ha eliminado el arista, no lo va a
		// volver a eliminar
		assertEquals(-4, this.graphCharacters.removeEdge(chars[i], chars[j]));
	    }
	}

	System.out.println("--- EL GRAFO DEBERIA ESTAR SIN ARISTAS:");
	System.out.println(this.graphCharacters.toString());
	System.out.println("---");
    }

    /**
     * Test edge addition. Dependent of 'testAddNode()'. DO NOT EXECUTE WITHOUT
     * INITIALICE THE GRAPH!
     */
    @Test
    private void testAddGetEdge() {
	Character problematicChar1 = evilChars[0];
	Character problematicChar2 = evilChars[1];

	// Bucle de 2 unicas iteraciones:
	// En la primera utiliza un peso valido para el arista
	// En la segunda utiliza un peso negativo
	for (int i = 0; i < 2; i++) {
	    int addedValue = (i == 0) ? 0 : -8;
	    int proposedWeight = 1 + addedValue;
	    Character nextChar = chars[0];

	    // El primer nodo no existe
	    assertEquals(-1 + addedValue, this.graphCharacters.addEdge(problematicChar1, nextChar, proposedWeight));
	    assertEquals(-1 + addedValue, this.graphCharacters.addEdge(problematicChar2, nextChar, proposedWeight));

	    // El segundo nodo no existe
	    assertEquals(-2 + addedValue, this.graphCharacters.addEdge(nextChar, problematicChar1, proposedWeight));
	    assertEquals(-2 + addedValue, this.graphCharacters.addEdge(nextChar, problematicChar2, proposedWeight));

	    // Ambos nodos no existen en el grafo
	    assertEquals(-3 + addedValue,
		    this.graphCharacters.addEdge(problematicChar2, problematicChar1, proposedWeight));
	    assertEquals(-3 + addedValue,
		    this.graphCharacters.addEdge(problematicChar1, problematicChar2, proposedWeight));
	    assertEquals(-3 + addedValue,
		    this.graphCharacters.addEdge(problematicChar1, problematicChar1, proposedWeight));
	    assertEquals(-3 + addedValue,
		    this.graphCharacters.addEdge(problematicChar2, problematicChar2, proposedWeight));

	    // Peso inválido, ambos nodos existen
	    if (i == 1)
		assertEquals(addedValue, this.graphCharacters.addEdge(nextChar, nextChar, addedValue));
	}

	Random random = new Random();
	double[][] generatedWeights = new double[this.graphSize][this.graphSize];

	for (int i = 0; i < this.graphSize; i++) {
	    // Verificamos el comportamiento de 'getNode()' para aristas aun sin insertar
	    for (int j = 0; j < this.graphSize; j++)
		assertEquals(-4, this.graphCharacters.getEdge(chars[i], chars[j]));

	    // Entonces las insertamos
	    for (int j = 0; j < this.graphSize; j++) {
		double nextWeight = random.nextDouble() + 1;
		generatedWeights[i][j] = nextWeight;
		// Se inserta el arista correctamente
		assertEquals(0, this.graphCharacters.addEdge(chars[i], chars[j], nextWeight));
		// Si lo volvemos a intentar, como ya existe un arista, no lo va a meter
		assertEquals(-4, this.graphCharacters.addEdge(chars[i], chars[j], nextWeight));
	    }
	}

	// --- Test del 'getEdge()'
	int proposedWeight = 1;
	Character nextChar = chars[0];
	// El primer nodo no existe
	assertEquals(-1, this.graphCharacters.addEdge(problematicChar1, nextChar, proposedWeight));
	assertEquals(-1, this.graphCharacters.addEdge(problematicChar2, nextChar, proposedWeight));

	// El segundo nodo no existe
	assertEquals(-2, this.graphCharacters.addEdge(nextChar, problematicChar1, proposedWeight));
	assertEquals(-2, this.graphCharacters.addEdge(nextChar, problematicChar2, proposedWeight));

	// Ambos nodos no existen en el grafo
	assertEquals(-3, this.graphCharacters.addEdge(problematicChar2, problematicChar1, proposedWeight));
	assertEquals(-3, this.graphCharacters.addEdge(problematicChar1, problematicChar2, proposedWeight));
	assertEquals(-3, this.graphCharacters.addEdge(problematicChar1, problematicChar1, proposedWeight));
	assertEquals(-3, this.graphCharacters.addEdge(problematicChar2, problematicChar2, proposedWeight));

	// Se devuelven las aristas existentes tal y como las habiamos introducido
	for (int i = 0; i < this.graphSize; i++)
	    for (int j = 0; j < this.graphSize; j++)
		assertEquals(generatedWeights[i][j], this.graphCharacters.getEdge(chars[i], chars[j]), 0.01);

    }

    /**
     * Test node addition. DO NOT EXECUTE WITHOUT INITIALICE THE GRAPH!
     */
    @Test
    private void testAddNode() {
	// 'null' no es un nodo
	assertEquals(-4, this.graphCharacters.addNode(null));
	// Insercion de nodos, incluso despues de haberse llenado el grafo
	System.out.println("--- EL GRAFO DEBERIA ESTAR VACIO:");
	System.out.println(this.graphCharacters.toString());
	System.out.println("---");
	for (int i = 0; i < chars.length; i++) {
	    Character nextChar = chars[i];
	    int loQueDevuelve = (i < this.graphSize) ? 0 : -2;
	    assertEquals(loQueDevuelve, this.graphCharacters.addNode(nextChar)); // Se agregan nodos que quepan. Los que
										 // no, no.
	}
	System.out.println(
		"--- EL GRAFO DEBERIA ESTAR LLENO (De la " + chars[0] + " a la " + chars[(this.graphSize - 1)] + "):");
	System.out.println(this.graphCharacters.toString());
	System.out.println("---");
	// Los nodos que ya existen no se pueden volver a agregar
	for (int i = 0; i < this.graphSize; i++) {
	    Character nextChar = chars[i];
	    assertTrue(this.graphCharacters.existsNode(nextChar));
	    assertEquals(-3, this.graphCharacters.addNode(nextChar));
	}
    }

}