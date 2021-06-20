package p4Hash;

/**
 * Clase OpenHashTable que simula una tabla Hash abierta
 * 
 * @author Ivan Alvarez Lopez UO264862
 * @version 11.12.2019
 * @param <T> Tipo generico comparable
 */
public class OpenHashTable<T extends Comparable<T>> extends AbstractHash<T> {

	protected OpenHashNode<T> associativeArray[]; // Vector que contiene en crudo a los elementos de la tabla Hash.
	protected int hashSize; // Tamano de la tabla Hash. Ha de ser siempre un numero primo.
	protected int numElems; // Cantidad de elementos de la tabla Hash en un momento dado.

	/**
	 * Constructor para fijar el tamano al numero primo mayor o igual que el
	 * parametro
	 * 
	 * @param tam Tamano de la tabla Hash
	 */
	@SuppressWarnings("unchecked")
	public OpenHashTable(int tam) {
		this.hashSize = nextPrimeNumber(tam);// Establece un tamano valido si tam no es primo
		this.associativeArray = (OpenHashNode<T>[]) new OpenHashNode[this.hashSize]; // Crea el vector de nodos Hash
		for (int i = 0; i < this.hashSize; i++)
			this.associativeArray[i] = new OpenHashNode<T>();
	}

	@Override
	public int add(T elem) {
		if (elem == null)
			return -2;
		int value = this.associativeArray[(fHash(elem) % getSize())].add(elem);
		if (value == 0)
			this.numElems++;
		return value;
	}

	@Override
	public T find(T elem) {
		if (elem == null)
			return elem;
		return this.associativeArray[(fHash(elem) % getSize())].find(elem);
	}

	@Override
	public int remove(T elem) {
		if (elem == null)
			return -2;
		int value = this.associativeArray[(fHash(elem) % getSize())].remove(elem);
		if (value == 0)
			this.numElems--;
		return value;
	}

	@Override
	protected boolean reDispersion() {
		return false;
	}

	@Override
	protected boolean inverseReDispersion() {
		return false;
	}

	@Override
	public int getNumOfElems() {
		return this.numElems;
	}

	@Override
	public int getSize() {
		return this.hashSize;
	}

	@Override
	public String toString() {
		StringBuilder cadena = new StringBuilder();
		for (int i = 0; i < getSize(); i++) {
			cadena.append("---> Open node " + i + ":\n");
			cadena.append(this.associativeArray[i].cell.toString());
			cadena.append("\n");
		}
		cadena.append("[Num.Cells.: ");
		cadena.append(getSize());
		cadena.append(" Num.Elems.: ");
		cadena.append(getNumOfElems());
		cadena.append("]");
		return cadena.toString();
	}
}
