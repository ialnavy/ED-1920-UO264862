package p3Arboles;

/**
 * Clase BSTree: Grafo conexo desde un nodo especial llamado raiz, desde el que
 * hay un unico camino hasta el resto de nodos y no tiene ciclos.
 * 
 * @author Ivan Alvarez Lopez UO264862
 * @version 7.11.2019
 */
public class BSTree<T extends Comparable<T>> {

	private BSTNode<T> root;

	/**
	 * Anade un nodo en el arbol
	 * 
	 * @param info Informacion a anadir
	 * @return 0 si lo anade; -1 si ya existe un nodo con la informacion dada; -2 en
	 *         otro caso
	 */
	public int addNode(T info) {
		if (info == null)
			return -2;
		if (searchNode(info) != null)
			return -1;
		this.root = addNodeRec(this.root, info);
		return 0;
	}

	/**
	 * Metodo privado recursivo para anadir un nodo en un subarbol, dada su raiz
	 * 
	 * @param subRoot Raiz del subarbol
	 * @param info    Informacion a anadir
	 * @return Raiz del subarbol actualizada
	 */
	private BSTNode<T> addNodeRec(BSTNode<T> subRoot, T info) {
		if (subRoot == null)
			return new BSTNode<T>(info);

		if (subRoot.getInfo().compareTo(info) > 0)
			subRoot.setLeft(addNodeRec(subRoot.getLeft(), info));
		else
			subRoot.setRight(addNodeRec(subRoot.getRight(), info));

		return subRoot;
	}

	/**
	 * Busca un nodo con una informacion dada en el arbol
	 * 
	 * @param info Informacion del nodo a buscar
	 * @return Informacion del nodo encontrado; null si no lo encuentra
	 */
	public T searchNode(T info) {
		if (info == null)
			return null;
		return searchNodeRec(this.root, info);
	}

	/**
	 * Metodo privado recursivo para buscar un nodo con una informacion dada en un
	 * subarbol
	 * 
	 * @param subRoot Raiz del subarbol
	 * @param info    Informacion del nodo a buscar
	 * @return Informacion del nodo encontrado; null si no lo encuentra
	 */
	private T searchNodeRec(BSTNode<T> subRoot, T info) {
		if (subRoot == null)
			return null;

		if (subRoot.getInfo().compareTo(info) > 0)
			return searchNodeRec(subRoot.getLeft(), info);
		else if (subRoot.getInfo().compareTo(info) < 0)
			return searchNodeRec(subRoot.getRight(), info);

		return subRoot.getInfo();
	}

	/**
	 * Devuelve el recorrido en preorden del arbol
	 * 
	 * @return Recorrido en preorden
	 */
	public String preOrder() {
		return preOrderRec(this.root);
	}

	/**
	 * Metodo privado recursivo para encontrar el recorrido en preorden de un
	 * subarbol dada su raiz
	 * 
	 * @param subRoot Raiz del subarbol
	 * @return Recorrido en preorden del subarbol
	 */
	private String preOrderRec(BSTNode<T> subRoot) {
		if (subRoot == null)
			return "";

		return subRoot.toString() + "\t" + preOrderRec(subRoot.getLeft()) + preOrderRec(subRoot.getRight());
	}

	/**
	 * Devuelve el recorrido en postorden del arbol
	 * 
	 * @return Recorrido en postorden
	 */
	public String postOrder() {
		return postOrderRec(this.root);
	}

	/**
	 * Metodo privado recursivo para encontrar el recorrido en postorden de un
	 * subarbol dada su raiz
	 * 
	 * @param subRoot Raiz del subarbol
	 * @return Recorrido en postorden del subarbol
	 */
	private String postOrderRec(BSTNode<T> subRoot) {
		if (subRoot == null)
			return "";

		return postOrderRec(subRoot.getLeft()) + postOrderRec(subRoot.getRight()) + subRoot.toString() + "\t";
	}

	/**
	 * Devuelve el recorrido en inorden del arbol
	 * 
	 * @return Recorrido en inorden
	 */
	public String inOrder() {
		return inOrderRec(this.root);
	}

	/**
	 * Metodo privado recursivo para encontrar el recorrido en inorden de un
	 * subarbol dada su raiz
	 * 
	 * @param subRoot Raiz del subarbol
	 * @return Recorrido en inorden del subarbol
	 */
	private String inOrderRec(BSTNode<T> subRoot) {
		if (subRoot == null)
			return "";

		return inOrderRec(subRoot.getLeft()) + subRoot.toString() + "\t" + inOrderRec(subRoot.getRight());
	}

	/**
	 * Elimina un nodo dada su informacion
	 * 
	 * @param info Informacion del nodo a eliminar
	 * @return 0 si lo elimina; -1 si no existe; -2 en otro caso
	 */
	public int removeNode(T info) {
		if (info == null)
			return -2;
		if (searchNode(info) == null)
			return -1;

		this.root = removeNodeRec(this.root, info);
		return 0;
	}

	/**
	 * Metodo privado recursivo que elimina un nodo de un subarbol dada su raiz
	 * 
	 * @param subRoot Raiz del subarbol
	 * @param info    Informacion del nodo a eliminar
	 * @return Raiz del subarbol actualizada
	 */
	private BSTNode<T> removeNodeRec(BSTNode<T> subRoot, T info) {
		if (subRoot == null)
			return subRoot;

		if (subRoot.getInfo().compareTo(info) > 0)
			subRoot.setLeft(removeNodeRec(subRoot.getLeft(), info));
		else if (subRoot.getInfo().compareTo(info) < 0)
			subRoot.setRight(removeNodeRec(subRoot.getRight(), info));
		else {
			if (subRoot.getLeft() != null && subRoot.getRight() != null) {

				BSTNode<T> iteratedNode = subRoot.getLeft();
				while (iteratedNode.getRight() != null)
					iteratedNode = iteratedNode.getRight();
				T greatestOfTheSmaller = iteratedNode.getInfo();

				subRoot.setInfo(greatestOfTheSmaller);
				subRoot.setLeft(removeNodeRec(subRoot.getLeft(), greatestOfTheSmaller));

			} else if (subRoot.getLeft() != null)
				return subRoot.getLeft();
			else if (subRoot.getRight() != null)
				return subRoot.getRight();
			else
				return null;
		}
		return subRoot;
	}

	/**
	 * Asigna un nodo como raiz del arbol
	 * 
	 * @param node Nueva raiz del arbol
	 */
	protected void setRoot(BSTNode<T> node) {
		this.root = node;
	}

	/**
	 * Devuelve la raiz del arbol
	 * 
	 * @return Raiz del arbol
	 */
	protected BSTNode<T> getRoot() {
		return this.root;
	}

	public String toString() {
		return tumbarArbol(root, 0);
	}

	/**
	 * 'toString()' para pruebas
	 * 
	 * @param n Nodo a imprimir
	 * @return Informacion del nodo
	 */
	protected String toStringParaPruebas(BSTNode<T> n) {
		if (n == null)
			return "_ ";

		return n + " " + // tambien valdria n.toString()
				toStringParaPruebas(n.getLeft()) + toStringParaPruebas(n.getRight());
	}

	/**
	 * Genera un String con el arbol "tumbado" (la raiz a la izquierda y las ramas
	 * hacia la derecha) Es un recorrido InOrden-Derecha-Izquierda, tabulando para
	 * mostrar los distintos niveles Utiliza el toString de la informacion
	 * almacenada
	 * 
	 * @param p   La raiz del arbol a mostrar tumbado
	 * @param esp El espaciado en numero de tabulaciones para indicar la profundidad
	 * @return El String generado
	 */
	protected String tumbarArbol(BSTNode<T> p, int esp) {
		StringBuilder cadena = new StringBuilder();

		if (p != null) {
			cadena.append(tumbarArbol(p.getRight(), esp + 1));
			for (int i = 0; i < esp; i++)
				cadena.append("\t");
			cadena.append(p + "\n");
			cadena.append(tumbarArbol(p.getLeft(), esp + 1));
		}
		return cadena.toString();
	}

}