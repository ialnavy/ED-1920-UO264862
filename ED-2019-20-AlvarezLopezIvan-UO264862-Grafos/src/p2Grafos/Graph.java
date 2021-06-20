package p2Grafos;

import java.text.DecimalFormat;

/**
 * Clase Graph.
 * 
 * @author Ivan Alvarez Lopez - UO264862
 * @version 17.10.2019
 * @param <T> Tipo de los nodos del grafo
 */
public class Graph<T> {
    protected T[] nodes; // Vector de nodos
    protected boolean[][] edges; // matriz de aristas
    protected double[][] weights; // matriz de pesos
    protected int numNodes; // n�mero de elementos en un momento dado
    protected double aFloyd[][];
    protected int pFloyd[][];

    /**
     * Se le pasa el numero maximo de nodos del grafo
     * 
     * @param tam Tamano del grafo
     */
    @SuppressWarnings("unchecked")
    public Graph(int tam) {
	this.nodes = (T[]) new Object[tam];
	this.edges = new boolean[tam][tam];
	this.weights = new double[tam][tam];
	this.numNodes = 0;
    }

    /**
     * Constructor para la evaluacion de la clase Graph.
     * 
     * @param tam            Tamano del grafo
     * @param initialNodes   Nodos del grafo
     * @param initialEdges   Matriz de adyacencia del grafo
     * @param initialWeights Matriz de aristas del grafo
     */
    public Graph(int tam, T initialNodes[], boolean[][] initialEdges, double[][] initialWeights) {
	// Llama al constructor original
	this(tam);

	// Pero modifica los atributos con los parametros para hacer pruebas...
	numNodes = initialNodes.length;

	for (int i = 0; i < numNodes; i++) {
	    // Si el vector de nodos se llama de otra forma (distinto de "nodes"), cambiad
	    // el nombre en la linea de abajo
	    nodes[i] = initialNodes[i];
	    for (int j = 0; j < numNodes; j++) {
		// Si la matriz de aristas se llama de otra forma (distinto de "edges"), cambiad
		// el nombre en la linea de abajo
		edges[i][j] = initialEdges[i][j];
		// Si la matriz de pesos se llama de otra forma (distinto de "weights"), cambiad
		// el nombre en la linea de abajo
		weights[i][j] = initialWeights[i][j];
	    }
	}

    }

    /**
     * Constructor para la evaluacion de la clase Graph.
     * 
     * @param tam            Tamano del grafo
     * @param initialNodes   Nodos del grafo
     * @param initialEdges   Matriz de adyacencia del grafo
     * @param initialWeights Matriz de aristas del grafo
     * @param initialAfloyd  Matriz A de Floyd
     * @param initialPfloyd  Matriz P de Floyd
     */
    public Graph(int tam, T initialNodes[], boolean[][] initialEdges, double[][] initialWeights,
	    double[][] initialAfloyd, int[][] initialPfloyd) {
	// Llama al constructor anterior de inicializaci�n
	this(tam, initialNodes, initialEdges, initialWeights);

	// Pero modifica los atributos que almacenan las matrices de Floyd con los
	// par�metros para hacer pruebas...

	if (initialAfloyd != null && initialPfloyd != null) {
	    // Si la matriz A de floyd se llama de otra forma (distinto de "aFloyd"),
	    // cambiad el nombre en la linea de abajo
	    aFloyd = initialAfloyd;
	    // Si la matriz P de floyd se llama de otra forma (distinto de "pFloyd"),
	    // cambiad el nombre en la linea de abajo
	    pFloyd = initialPfloyd;
	}

    }

    /**
     * Lanza el recorrido en profundidad de un grafo desde un nodo determinado, No
     * necesariamente recorre todos los nodos. Al recorrer cada nodo a�ade el
     * toString del nodo y un tabulador Se puede usar un m�todo privado recursivo...
     * Se recorren vecinos empezando por el principio del vector de nodos (antes
     * �ndices bajos) Si no existe el nodo devuelve una cadena vacia
     * 
     * @param origin Nodo origen
     * @return Recorrido en profundidad desde ese nodo
     */
    public String recorridoProfundidad(T origin) {
	int originIndex = getNode(origin);

	if (originIndex == -1)
	    return "";

	return recorridoProfundidadRecursivo(originIndex, new boolean[this.numNodes]);
    }

    /**
     * Metodo recursivo interno para hallar el camino en profundidad desde un nodo
     * dado
     * 
     * @param origin  Nodo origen
     * @param visited Vector que indica si un nodo ha sido visitado
     * @return Recorrido en profundidad
     */
    private String recorridoProfundidadRecursivo(int origin, boolean[] visited) {
	String recorrido = this.nodes[origin].toString();
	for (int j = 0; j < this.numNodes; j++) {
	    if (!visited[j] && this.edges[origin][j]) {
		visited[j] = true;
		recorrido += "\t" + recorridoProfundidadRecursivo(j, visited);
	    }
	}
	return recorrido;
    }

    /**
     * Indica el camino entre los nodos que se le pasan como par�metros en un String
     * de esta forma:
     * Origen\t(coste0)\tIntermedio1\t(coste1)�.IntermedioN\t(costeN)Destino Si no
     * hay camino: Origent(Infinity)\tDestino Si Origen y Destino coinciden: Origen
     * Si no existen los 2 nodos devuelve una cadena vac�a
     * 
     * @param origin      Nodo origen
     * @param destination Nodo destino
     * @return Camino entre el origen y el destino
     */
    public String path(T origin, T destination) {
	int originIndex = getNode(origin);
	int destinationIndex = getNode(destination);

	if (originIndex < 0 || destinationIndex < 0)
	    return "";
	if (originIndex == destinationIndex)
	    return this.nodes[originIndex].toString();

	executeFloydIfNecessary();
	if (this.pFloyd[originIndex][destinationIndex] == -1)
	    return this.nodes[originIndex].toString() + "\t" + "(Infinity)" + "\t"
		    + this.nodes[destinationIndex].toString();
	return this.nodes[originIndex].toString() + recursivePath(originIndex, destinationIndex);
    }

    /**
     * Metodo privado recursivo para el path
     * 
     * @param origin      Indice del nodo origen
     * @param destination Indice del nodo destino
     * @return Camino entre el origen y el destino
     */
    private String recursivePath(int origin, int destination) {
	int previous = this.pFloyd[origin][destination];
	String localPath = "";
	if (previous == origin)
	    localPath += "\t" + this.nodes[destination].toString();
	else
	    localPath += recursivePath(origin, previous) + "\t" + this.nodes[destination].toString();
	return localPath;
    }

    /**
     * Ejecuta el algoritmo de Floyd-Warshall si no se ha ejecutado previamente.
     */
    private void executeFloydIfNecessary() {
	if (this.aFloyd == null || this.pFloyd == null)
	    this.floyd();
    }

    /**
     * Anula una ejecucion previa del algoritmo de Floyd-Warshall
     */
    private void invalidateFloyd() {
	this.aFloyd = null;
	this.pFloyd = null;
    }

    /**
     * Devuelve el coste del camino de coste minimo entre origen y destino segun
     * Floyd Si no est�n generadas las matrices de Floyd, las genera. Si no puede
     * obtener el valor por alguna razon, devuelve �1 (OJO que es distinto de
     * infinito)
     * 
     * @param origin      Nodo de origen
     * @param destination Nodo de destino
     * @return Coste minimo
     */
    public double minCostPath(T origin, T destination) {
	int originIndex = getNode(origin);
	int destinationIndex = getNode(destination);

	if (originIndex < 0 || destinationIndex < 0)
	    return -1;

	executeFloydIfNecessary();
	return this.aFloyd[originIndex][destinationIndex];
    }

    /**
     * Devuelve la matriz A de Floyd, con infinito si no hay camino. Si no se ha
     * invocado a Floyd debe devolver null
     * 
     * @return Matriz A de Floyd
     */
    protected double[][] getAFloyd() {
	return this.aFloyd;
    }

    /**
     * Devuelve la matriz P de Floyd, con -1 en las posiciones en las que no hay
     * nodo intermedio. Si no se ha invocado a Floyd debe devolver null
     * 
     * @return Matriz P de Floyd
     */
    protected int[][] getPFloyd() {
	return this.pFloyd;
    }

    /**
     * Aplica el algoritmo de Floyd al grafo
     * 
     * @return 0 si lo aplica y genera matrices A y P; -1 si no lo hace
     */
    public int floyd() {
	if (this.numNodes <= 0)
	    return -1;

	double[][] A = initialiceAFloyd(); // Matriz de Costes M�nimos
	int[][] P = initialicePFloyd(); // Matriz de Rutas de Coste M�nimo

	for (int intermediate = 0; intermediate < this.numNodes; intermediate++) {
	    for (int origin = 0; origin < this.numNodes; origin++) {
		for (int destination = 0; destination < this.numNodes; destination++) {
		    if (A[origin][intermediate] + A[intermediate][destination] < A[origin][destination]) {
			A[origin][destination] = A[origin][intermediate] + A[intermediate][destination];
			P[origin][destination] = intermediate;
		    }
		}
	    }
	}

	this.aFloyd = A;
	this.pFloyd = P;

	return 0;
    }

    /**
     * Inicializa una matriz A de Costes Minimos para el inicio de la ejecucion del
     * algoritmo de Floyd-Warshall
     * 
     * @return Matriz A previa a la ejecucion de Floyd-Warshall
     */
    private double[][] initialiceAFloyd() {
	double[][] A = new double[this.numNodes][this.numNodes];

	for (int i = 0; i < this.numNodes; i++)
	    for (int j = 0; j < this.numNodes; j++)
		A[i][j] = (this.edges[i][j]) ? this.weights[i][j] : Double.POSITIVE_INFINITY;

	return A;
    }

    /**
     * Inicializa una matriz P de Rutas de Coste Minimo para el inicio de la
     * ejecucion del algoritmo de Floyd-Warshall
     * 
     * @return Matriz P previa a la ejecucion de Floyd-Warshall
     */
    private int[][] initialicePFloyd() {
	int[][] P = new int[this.numNodes][this.numNodes];

	for (int i = 0; i < this.numNodes; i++)
	    for (int j = 0; j < this.numNodes; j++)
		P[i][j] = this.edges[i][j] ? i : -1;

	return P;
    }

    /**
     * Algoritmo de Dijkstra para encontrar el camino de coste m�nimo desde
     * nodoOrigen hasta el resto de nodos. Devuelve el vector D costes minimos de
     * Dijkstra. D(j) = min[0,numNodes){ D(j) , D(k) + L(k,j) }
     * 
     * @param nodoOrigen Nodo de origen
     * @return Vector D de costes m�nimos de Dijkstra
     */
    public double[] dijkstra(T nodoOrigen) {
	// System.out.println("---> Algoritmo de Dijkstra:");
	// System.out.println("Se ha llamado a Dijkstra, tomando el nodo " +
	// nodoOrigen.toString() + " como origen.");
	int originIndex = getNode(nodoOrigen);
	// Conjunto S de nodos ya evaluados
	boolean[] S = new boolean[this.numNodes];
	S[originIndex] = true;
	// Vector P de rutas de coste minimo
	int[] P = initialicePDijkstra(originIndex);
	// Vector D de costes minimos
	double[] D = initialiceDDijkstra(originIndex);

	// Cuerpo del algoritmo de Dijkstra
	// --------------------------------
	// Compara los dos caminos:
	// - Desde origen hasta destino
	// - Desde origen hasta intermedio + Desde intermedio hasta destino
	// Se queda con el que tenga menos coste
	int intermediateIndex = minCost(D, S);
	while (intermediateIndex != -1) {
	    for (int destinationIndex = 0; destinationIndex < this.numNodes; destinationIndex++) {
		double caminoSinPasarPorElNodoIntermedio = D[destinationIndex];
		if (this.edges[intermediateIndex][destinationIndex]) {
		    double caminoPasandoPorElNodoIntermedio = D[intermediateIndex]
			    + this.weights[intermediateIndex][destinationIndex];
		    // Cambia el camino si encuentra una ruta de menor coste
		    if (caminoPasandoPorElNodoIntermedio < caminoSinPasarPorElNodoIntermedio) {
			D[destinationIndex] = caminoPasandoPorElNodoIntermedio;
			P[destinationIndex] = intermediateIndex;
		    }
		}
	    }
	    // Ya ha evaluado el nodo intermedio, reclama uno siguiente
	    S[intermediateIndex] = true;
	    intermediateIndex = minCost(D, S);
	}

	// System.out.println("---> Fin del algoritmo de Dijkstra:");
	return D;
    }

    /**
     * Busca el nodo con distancia minima en D al resto de nodos, se le pasa el
     * vector D de dijkstra y el conjunto de visitados (como un vector de booleanos)
     * y devuelve el indice del nodo buscado o -1 si el grafo es no conexo o no
     * quedan nodos sin visitar
     * 
     * @param D Vector de costes minimos
     * @param S Vector de nodos ya evaluados
     * @return Nodo cuyo coste para llegar a el es el menor
     */
    private int minCost(double[] D, boolean[] S) {
	int minNode = -1;
	double minWeight = Double.POSITIVE_INFINITY;
	for (int i = 0; i < this.numNodes; i++) {
	    if (!S[i] && D[i] < minWeight && D[i] > 0) {
		minNode = i;
		minWeight = D[i];
	    }
	}
	return minNode;
    }

    /**
     * Inicializa un vector P de rutas de coste minimo para el inicio de la
     * ejecucion del algoritmo de Dijkstra
     * 
     * @param originIndex Nodo origen
     * @return Vector P previo a la ejecucion de Dijkstra
     */
    private int[] initialicePDijkstra(int originIndex) {
	int[] P = new int[this.numNodes];

	for (int i = 0; i < this.numNodes; i++)
	    P[i] = this.edges[originIndex][i] ? originIndex : -1;
	P[originIndex] = originIndex;

	return P;
    }

    /**
     * Inicializa un vector D de costes minimos para el inicio de la ejecucion del
     * algoritmo de Dijkstra
     * 
     * @param originIndex Nodo origen
     * @return Vector D previo a la ejecucion de Dijkstra
     */
    private double[] initialiceDDijkstra(int originIndex) {
	double[] D = this.weights[originIndex].clone();

	for (int i = 0; i < this.numNodes; i++)
	    D[i] = this.edges[originIndex][i] ? this.weights[originIndex][i] : Double.POSITIVE_INFINITY;
	D[originIndex] = 0;

	return D;
    }

    /**
     * Inserta un nuevo nodo que se le pasa como parametro. - Si ya existe, no lo
     * inserta y devuelve -1, (implementado mas tarde) - Si no cabe, no lo inserta y
     * devuelve -2. - Si se le pasa un nodo inv�lido -4 si lo inserta devuelve 0.
     * 
     * @param node Nodo a anadir
     * @return 0 si se anade el nodo; -1 si no existe el nodo; -2 si el grafo esta
     *         lleno; -4 si el nodo es invalido.
     */
    public int addNode(T node) {
	if (node == null)
	    return -4;
	int state = 0;
	if (existsNode(node))
	    state += -1;
	if (isFull())
	    state += -2;
	if (state == 0) {
	    this.nodes[this.numNodes] = node;
	    this.numNodes++;
	    invalidateFloyd();
	}
	return state;
    }

    /**
     * Evalua si el grafo est� lleno.
     * 
     * @return Esta lleno?
     */
    private boolean isFull() {
	return this.numNodes == this.nodes.length;
    }

    /**
     * Obtiene el �ndice de un nodo en el vector de nodos, y �1 si no existe
     * 
     * ��� OJO que es privado porque depende de la implementaci�n !!!
     * 
     * @param node Nodo a buscar
     * @return Indice del nodo; -1 si no existe.
     */
    protected int getNode(T node) {
	if (node != null) {
	    int i = 0;
	    while (i < this.numNodes) {
		if (this.nodes[i].equals(node))
		    return i;
		i++;
	    }
	}
	return -1;
    }

    /**
     * Inserta una arista con el peso indicado (mayor que 0) entre dos nodos, uno
     * origen y otro destino. Devuelve 0 si la inserta Devuelve �1 y -2 si no
     * existen nodos origen y destino respectivamente Devuelve �4 si ya existe y �8
     * si el peso no es v�lido Si se dan varios errores se suman
     * 
     * @param source     Nodo origen
     * @param target     Nodo destino
     * @param edgeWeight Peso del arista
     * @return El peso del grafo; -4 si no existe; -1 si no existe el nodo origen;
     *         -2 si no existe el nodo destino; -8 si el peso no existe; se suman
     *         los errores.
     */
    public int addEdge(T source, T target, double edgeWeight) {
	// Search for the requested nodes
	// If they do not exist, their indexes will be -1
	int sourceIndex = getNode(source);
	int targetIndex = getNode(target);

	// --------
	// ERRORS
	// --------
	// We must carry every bad state
	int stateFlag = 0;
	// [Invalid weight] Adds -8
	if (edgeWeight <= 0)
	    stateFlag += -8;
	// There is no node source, or no node target
	if (sourceIndex == -1 || targetIndex == -1) {
	    // [No node source] Adds -1
	    if (sourceIndex == -1)
		stateFlag += -1;
	    // [No node target] Adds -2
	    if (targetIndex == -1)
		stateFlag += -2;
	    // We cannot request later a negative position to a matrix
	    return stateFlag;
	}
	// The edge already exists
	if (existEdge(sourceIndex, targetIndex))
	    stateFlag += -4;
	// --------
	// If there is no error, we will add the edge to the graph
	if (stateFlag == 0) {
	    this.edges[sourceIndex][targetIndex] = true;
	    this.weights[sourceIndex][targetIndex] = edgeWeight;
	    invalidateFloyd();
	}
	return stateFlag;
    }

    /**
     * Indica si existe o no el nodo en el grafo
     * 
     * @param node Nodo a buscar
     * @return Existe el nodo?
     */
    public boolean existsNode(T node) {
	int nodeIndex = getNode(node);
	return (nodeIndex >= 0 && nodeIndex < this.numNodes);
    }

    /**
     * indica si existe o no el nodo en el grafo
     * 
     * @param sourceIndex Indice del nodo origen
     * @param targetIndex Indice del nodo destino
     * @return Existe el arista?
     */
    private boolean existEdge(int sourceIndex, int targetIndex) {
	if (sourceIndex >= 0 && targetIndex >= 0 && sourceIndex < this.numNodes && targetIndex < this.numNodes)
	    return this.edges[sourceIndex][targetIndex];
	else
	    return false;
    }

    /**
     * indica si existe o no el nodo en el grafo
     * 
     * @param source Nodo origen
     * @param target Nodo destino
     * @return Existe el arista?
     */
    public boolean existEdge(T source, T target) {
	return existEdge(getNode(source), getNode(target));
    }

    /**
     * Devuelve el peso de la arista que conecta dos nodos, devuelve �1, -2 Y �3 si
     * no existe origen, destino, ambos (notese que �3 es la suma de �1 y �2)
     * devuelve �4 si no existe la arista
     * 
     * @param source Nodo origen
     * @param target Nodo destino
     * @return El peso del grafo; -4 si no existe; -1 si no existe el nodo origen;
     *         -2 si no existe el nodo destino; -3 si no existen ni el nodo origen
     *         ni el nodo destino
     */
    public double getEdge(T source, T target) {
	int sourceIndex = getNode(source);
	int targetIndex = getNode(target);

	if (sourceIndex == -1 || targetIndex == -1) {
	    int errorFlag = 0;
	    if (sourceIndex == -1)
		errorFlag += -1;
	    if (targetIndex == -1)
		errorFlag += -2;
	    return errorFlag;
	}
	if (!existEdge(sourceIndex, targetIndex))
	    return -4;
	return this.weights[sourceIndex][targetIndex];
    }

    /**
     * Borra la arista del grafo que conecta dos nodos devuelve �1, -2 Y �3 si no
     * existe origen, destino, ambos (notese que �3 es la suma de �1 y �2) devuelve
     * �4 si no existe la arista devuelve 0 si la borra
     * 
     * @param source Nodo origen
     * @param target Nodo destino
     * @return 0 si la elimina; -4 si no existe; -1 si no existe el nodo origen; -2
     *         si no existe el nodo destino; -3 si no existen ni el nodo origen ni
     *         el nodo destino
     */
    public int removeEdge(T source, T target) {
	int sourceIndex = getNode(source);
	int targetIndex = getNode(target);

	if (sourceIndex == -1 || targetIndex == -1) {
	    int errorFlag = 0;
	    if (sourceIndex == -1)
		errorFlag += -1;
	    if (targetIndex == -1)
		errorFlag += -2;
	    return errorFlag;
	}
	if (!existEdge(sourceIndex, targetIndex))
	    return -4;

	this.edges[sourceIndex][targetIndex] = false;
	invalidateFloyd();
	return 0;
    }

    /**
     * Borra el nodo deseado del vector de nodos as� como las aristas de las que
     * forma parte, devolviendo 0 si lo hace y �1 si no existe el nodo y �4 si el
     * nodo no es valido
     * 
     * @param node Nodo a eliminar
     * @return 0 si lo elimina; -4 si es invalido; -1 si no existe
     */
    public int removeNode(T node) {
	if (node == null)
	    return -4;
	int state = 0;
	if (!existsNode(node))
	    state += -1;
	if (state == 0) {
	    int removedNodeIndex = getNode(node); // Nodo r
	    int lastNodeIndex = this.numNodes - 1; // Nodo l

	    // Recolocar fila final
	    for (int i = 0; i < lastNodeIndex; i++) {
		this.edges[removedNodeIndex][i] = this.edges[lastNodeIndex][i];
		this.weights[removedNodeIndex][i] = this.weights[lastNodeIndex][i];

		this.edges[i][removedNodeIndex] = this.edges[i][lastNodeIndex];
		this.weights[i][removedNodeIndex] = this.weights[i][lastNodeIndex];
	    }

	    // Recolocar elemento final de la traza
	    this.edges[removedNodeIndex][removedNodeIndex] = this.edges[lastNodeIndex][lastNodeIndex];
	    this.weights[removedNodeIndex][removedNodeIndex] = this.weights[lastNodeIndex][lastNodeIndex];

	    // Por ultimo, superponemos el ultimo nodo sobre el nodo eliminado
	    this.nodes[removedNodeIndex] = this.nodes[lastNodeIndex];
	    this.numNodes--;
	    invalidateFloyd();
	}
	return state;
    }

    /**
     * Devuelve un String con la informacion del grafo (incluyendo matrices de
     * Floyd)
     */
    public String toString() {
	DecimalFormat df = new DecimalFormat("#.##");
	String cadena = "";

	cadena += "NODES\n";
	for (int i = 0; i < numNodes; i++) {
	    cadena += nodes[i].toString() + "\t";
	}
	cadena += "\n\nEDGES\n";
	for (int i = 0; i < numNodes; i++) {
	    for (int j = 0; j < numNodes; j++) {
		if (edges[i][j])
		    cadena += "T\t";
		else
		    cadena += "F\t";
	    }
	    cadena += "\n";
	}
	cadena += "\nWEIGHTS\n";
	for (int i = 0; i < numNodes; i++) {
	    for (int j = 0; j < numNodes; j++) {

		cadena += (edges[i][j] ? df.format(weights[i][j]) : "-") + "\t";
	    }
	    cadena += "\n";
	}

	double[][] aFloyd = getAFloyd();
	if (aFloyd != null) {
	    cadena += "\nAFloyd\n";
	    for (int i = 0; i < numNodes; i++) {
		for (int j = 0; j < numNodes; j++) {
		    cadena += df.format(aFloyd[i][j]) + "\t";
		}
		cadena += "\n";
	    }
	}

	int[][] pFloyd = getPFloyd();
	if (pFloyd != null) {
	    cadena += "\nPFloyd\n";
	    for (int i = 0; i < numNodes; i++) {
		for (int j = 0; j < numNodes; j++) {
		    cadena += (pFloyd[i][j] >= 0 ? df.format(pFloyd[i][j]) : "-") + "\t";
		}
		cadena += "\n";
	    }
	}
	return cadena;
    }
}