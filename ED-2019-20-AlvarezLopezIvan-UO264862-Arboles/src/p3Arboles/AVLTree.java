package p3Arboles;

/**
 * Clase derivada de BSTree implementando funcionalidad de AVL
 * 
 * @author Ivan Alvarez Lopez - UO264862
 * @version 7.11.2019
 */
public class AVLTree<T extends Comparable<T>> extends BSTree<T> {

    /*
     * (non-Javadoc)
     * 
     * @see BSTree#addNode(java.lang.Comparable) Redefine inserción para
     * funcionalidad AVL
     */
    public int addNode(T info) {
	if (info == null)
	    return -2;
	if (searchNode(info) != null)
	    return -1;
	setRoot(addNodeRec(getRoot(), info));
	return 0;
    }

    /**
     * Metodo privado recursivo para anadir un nodo en un subarbol AVL, dada su raiz
     * 
     * @param subRoot Raiz del subarbol
     * @param info    Informacion a anadir
     * @return Raiz del subarbol actualizada
     */
    private AVLNode<T> addNodeRec(AVLNode<T> subRoot, T info) {
	if (subRoot == null)
	    return new AVLNode<T>(info);

	if (subRoot.getInfo().compareTo(info) > 0)
	    subRoot.setLeft(addNodeRec(subRoot.getLeft(), info));
	else
	    subRoot.setRight(addNodeRec(subRoot.getRight(), info));

	return updateAndBalanceIfNecesary(subRoot);
    }

    /**
     * Se le pasa el nodo raiz del subarbol que se quiere actualizar Height, BF y
     * balancear si fuese necesario. Devuelve la raiz del arbol por si ha cambiado
     * 
     * @param node Nodo raiz del subarbol
     * @return Nodo raiz del subarbol actualizado
     */
    private AVLNode<T> updateAndBalanceIfNecesary(AVLNode<T> node) {
	if (node != null) {
	    node.updateHeight();

	    if (node.getBF() == 2) {
		// Rotaciones derechas
		if (node.getRight().getBF() == 1)
		    node = singleRightRotation(node);
		else if (node.getRight().getBF() == 0)
		    node = singleRightRotation(node);
		else if (node.getRight().getBF() == -1)
		    node = doubleRightRotation(node);
	    } else if (node.getBF() == -2) {
		// Rotaciones izquierdas
		if (node.getLeft().getBF() == -1)
		    node = singleLeftRotation(node);
		else if (node.getLeft().getBF() == 0)
		    node = singleLeftRotation(node);
		else if (node.getLeft().getBF() == 1)
		    node = doubleLeftRotation(node);
	    }
	}
	return node;
    }

    /**
     * Se le pasa la raiz del arbol a balancear con rotacion simple devuelve la raiz
     * del nuevo arbol que ha cambiado
     * 
     * @param subRoot Raiz del subarbol a balancear
     * @return Raiz del subarbol actualizada
     */
    private AVLNode<T> singleLeftRotation(AVLNode<T> subRoot) {
	// System.out.println("Se ha provocado: SLR(" + subRoot.getInfo() + ")");
	AVLNode<T> aux = subRoot.getLeft();
	subRoot.setLeft(aux.getRight());
	aux.setRight(updateAndBalanceIfNecesary(subRoot));
	return updateAndBalanceIfNecesary(aux);
    }

    /**
     * Se le pasa la raiz del arbol a balancear con rotacion simple devuelve la raiz
     * del nuevo arbol que ha cambiado
     * 
     * @param subRoot Raiz del subarbol a balancear
     * @return Raiz del subarbol actualizada
     */
    private AVLNode<T> singleRightRotation(AVLNode<T> subRoot) {
	// System.out.println("Se ha provocado: SRR(" + subRoot.getInfo() + ")");
	AVLNode<T> aux = subRoot.getRight();
	subRoot.setRight(aux.getLeft());
	aux.setLeft(updateAndBalanceIfNecesary(subRoot));
	return updateAndBalanceIfNecesary(aux);
    }

    /**
     * Se le pasa la raiz del arbol a balancear con rotacion doble devuelve la raiz
     * del nuevo arbol que ha cambiado
     * 
     * @param subRoot Raiz del subarbol a balancear
     * @return Raiz del subarbol actualizada
     */
    private AVLNode<T> doubleLeftRotation(AVLNode<T> subRoot) {
	// System.out.println("Se ha provocado: DLR(" + subRoot.getInfo() + ")");
	subRoot.setLeft(singleRightRotation(subRoot.getLeft()));
	return singleLeftRotation(subRoot);
    }

    /**
     * Se le pasa la raiz del arbol a balancear con rotacion doble Devuelve la raiz
     * del nuevo arbol que ha cambiado
     * 
     * @param subRoot Raiz del subarbol a balancear
     * @return Raiz del subarbol actualizada
     */
    private AVLNode<T> doubleRightRotation(AVLNode<T> subRoot) {
	// System.out.println("Se ha provocado: DRR(" + subRoot.getInfo() + ")");
	subRoot.setRight(singleLeftRotation(subRoot.getRight()));
	return singleRightRotation(subRoot);
    }

    /*
     * (non-Javadoc)
     * 
     * @see BSTree#removeNode(java.lang.Comparable) Redefinición para incluir
     * características AVL
     */
    public int removeNode(T info) {
	if (info == null)
	    return -2;
	if (searchNode(info) == null)
	    return -1;
	setRoot(removeNodeRec(getRoot(), info));
	return 0;
    }

    /**
     * Metodo privado recursivo que elimina un nodo de un subarbol AVL dada su raiz
     * y lo reequilibra
     * 
     * @param subRoot Raiz del subarbol
     * @param info    Informacion del nodo a eliminar
     * @return Raiz del subarbol actualizada y equilibrada
     */
    private AVLNode<T> removeNodeRec(AVLNode<T> subRoot, T info) {
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
	return updateAndBalanceIfNecesary(subRoot);
    }

    /**
     * Devuelve el nodo raiz del arbol
     * 
     * @return Raiz del arbol
     */
    protected AVLNode<T> getRoot() {
	return (AVLNode<T>) super.getRoot();
    }
}
