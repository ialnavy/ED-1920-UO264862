package p3Arboles;

/**
 * Clase BSTNode. Nodo de un arbol binario
 * 
 * @author Ivan Alvarez Lopez UO264862
 * @version 23.10.2019
 */
public class BSTNode<T extends Comparable<T>> {

	private T info;
	private BSTNode<T> left;
	private BSTNode<T> right;

	/**
	 * Constructor al que se le pasa un elemento comparable.
	 * 
	 * @param info Elemento contenido por el nodo creado
	 */
	public BSTNode(T info) {
		this.setInfo(info);
	}

	/**
	 * Establece una informacion dada al nodo
	 * 
	 * @param info Nueva informacion del nodo
	 */
	protected void setInfo(T info) {
		this.info = info;
	}

	/**
	 * Devuelve la informacion del nodo
	 * 
	 * @return Informacion del nodo
	 */
	public T getInfo() {
		return this.info;
	}

	/**
	 * Establece como subarbol izquierdo un nuevo arbol encabezado por el nodo dado
	 * 
	 * @param node Nueva raiz del subarbol izquierdo
	 */
	public void setLeft(BSTNode<T> node) {
		this.left = node;
	}

	/**
	 * Establece como subarbol derecho un nuevo arbol encabezado por el nodo dado
	 * 
	 * @param node Nueva raiz del subarbol derecho
	 */
	public void setRight(BSTNode<T> node) {
		this.right = node;
	}

	/**
	 * Devuelve el nodo raiz del subarbol izquierdo
	 * 
	 * @return Nodo raiz del subarbol izquierdo
	 */
	protected BSTNode<T> getLeft() {
		return this.left;
	}

	/**
	 * Devuelve el nodo raiz del subarbol derecho
	 * 
	 * @return Nodo raiz del subarbol derecho
	 */
	protected BSTNode<T> getRight() {
		return this.right;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return info.toString();
	}
}