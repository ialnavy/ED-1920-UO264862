package p4Hash;

/**
 * Clase ClosedHashTable. Simula una tabla Hash cerrada.
 * 
 * @author Ivan Alvarez Lopez - UO264862
 * @version 4.12.2019
 * @param <T> Tipo generico
 */
public class ClosedHashTable<T> extends AbstractHash<T> {

	// Tipo de exploracion en caso de colision, por defecto sera LINEAL
	public static final int LINEAL = 0;
	public static final int CUADRATICA = 1;
	public static final int DOBLEHASH = 2;

	// IMPORTANTE: No cambiar el nombre ni visibilidad de los atributos protected
	protected HashNode<T> associativeArray[]; // Vector que contiene en crudo a los elementos de la tabla Hash.
	protected int hashSize; // Tamano de la tabla Hash. Ha de ser siempre un numero primo.
	protected int removedNodes;
	protected int numElems; // Cantidad de elementos de la tabla Hash en un momento dado.
	protected int exploracion; // En caso de colision, se compara este atributo con las distintas constantes

	// respectivas al tipo de exploracion que se llevara a cabo.
	protected double fcUP; // Umbral de factor de carga para redispersiones directas
	protected double fcDOWN; // Umbral de factor de carga para redispersiones inversas

	/**
	 * Constructor para fijar el tamano al numero primo mayor o igual que el
	 * parametro y el tipo de exploracion al indicado
	 * 
	 * @param tam  Tamano de la tabla Hash
	 * @param expl Constante que representa el tipo de exploracion a utilizar
	 */
	@SuppressWarnings("unchecked")
	public ClosedHashTable(int tam, int expl) {
		this.hashSize = nextPrimeNumber(tam);// Establece un tamano valido si tam no es primo
		this.removedNodes = 0;
		this.associativeArray = (HashNode<T>[]) new HashNode[this.hashSize]; // Crea el vector de nodos Hash
		for (int i = 0; i < this.hashSize; i++)
			this.associativeArray[i] = new HashNode<T>();
		setExploracion(expl);
		this.fcUP = 0.5;
		this.fcDOWN = 0.3;
	}

	/**
	 * Constructor para fijar el tamano al numero primo mayor o igual que el
	 * parametro
	 * 
	 * @param tam    Tamano del Hash, si no es un numero primo lo ajusta al primo
	 *               superior
	 * @param fcUP   Factor de carga limite, por encima del cual hay que redispersar
	 *               (directa)
	 * @param fcDOWN Factor de carga limite, por debajo del cual hay que redispersar
	 *               (inversa)
	 * @param expl   Tipo de exploracion (LINEAL=0, CUADRATICA=1, ...), si invalido
	 *               LINEAL
	 */
	public ClosedHashTable(int tam, double fcUP, double fcDOWN, int expl) {
		this(tam, expl);
		this.fcUP = fcUP;
		this.fcDOWN = fcDOWN;
	}

	/**
	 * Se valorara que interfiera lo menos posible en la complejidad del resto de
	 * operaciones de la clase, sobre todo de las que precisas rapidez de ejecucion:
	 * add, fin y remove.
	 * 
	 * @return Devuelve la relacion entre celdas borradas respecto al total
	 */
	public float getDeletedRate() {
		return (((float) this.removedNodes) / ((float) this.hashSize));
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
	public int add(T elem) {
		if (elem == null)
			return -2;

		int candidatePos = fHash(elem);
		int i = 0;

		HashNode<T> iteratedNode = this.associativeArray[candidatePos];
		while (iteratedNode.getStatus() == HashNode.LLENO) {
			if (candidatePos == fHash(elem) && i > 0)
				return -1;
			candidatePos = fExploracion(elem, ++i);
			iteratedNode = this.associativeArray[candidatePos];
		}
		this.associativeArray[candidatePos].setInfo(elem);
		this.numElems++;

		reDispersion();

		return 0;
	}

	@Override
	public T find(T elem) {
		if (elem == null)
			return elem;

		int candidatePos = fHash(elem);
		HashNode<T> iteratedNode = this.associativeArray[candidatePos];

		for (int i = 1; i <= this.hashSize; i++) {
			if (candidatePos < 0 || iteratedNode.getStatus() == HashNode.VACIO)
				break;

			if (iteratedNode.getStatus() == HashNode.LLENO && elem.equals(iteratedNode.getInfo()))
				return iteratedNode.getInfo();

			candidatePos = fExploracion(elem, i);
			iteratedNode = this.associativeArray[candidatePos];
		}
		return null;
	}

	@Override
	public int remove(T elem) {
		if (elem == null)
			return -2;

		int candidatePos = fHash(elem);
		int i = 0;
		HashNode<T> iteratedNode = this.associativeArray[candidatePos];

		while (iteratedNode.getStatus() != HashNode.VACIO) {
			if (iteratedNode.getStatus() == HashNode.LLENO && elem.equals(iteratedNode.getInfo())) {
				iteratedNode.remove();
				this.numElems--;
				this.removedNodes++;
				inverseReDispersion();
				deletedReDispersion();
				return 0;
			}
			candidatePos = fExploracion(elem, ++i);
			iteratedNode = this.associativeArray[candidatePos];
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected boolean reDispersion() {
		if (getFC() > this.fcUP) {
			HashNode<T>[] oldAssociativeArray = this.associativeArray;
			int oldHashSize = this.hashSize;
			this.removedNodes = 0;
			this.hashSize = nextPrimeNumber(this.hashSize * 2);
			this.associativeArray = (HashNode<T>[]) new HashNode[this.hashSize];
			for (int i = 0; i < this.hashSize; i++)
				this.associativeArray[i] = new HashNode<T>();
			this.numElems = 0;

			for (int i = 0; i < oldHashSize; i++) {
				HashNode<T> oldNode = oldAssociativeArray[i];
				if (oldNode.getStatus() == HashNode.LLENO)
					add(oldNode.getInfo());
			}
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected boolean inverseReDispersion() {
		if (getFC() < this.fcDOWN) {
			HashNode<T>[] oldAssociativeArray = this.associativeArray;
			int oldHashSize = this.hashSize;
			this.removedNodes = 0;
			this.removedNodes = 0;
			this.hashSize = previousPrimeNumber(this.hashSize / 2);
			this.associativeArray = (HashNode<T>[]) new HashNode[this.hashSize];
			for (int i = 0; i < this.hashSize; i++)
				this.associativeArray[i] = new HashNode<T>();
			this.numElems = 0;

			for (int i = 0; i < oldHashSize; i++) {
				HashNode<T> oldNode = oldAssociativeArray[i];
				if (oldNode.getStatus() == HashNode.LLENO)
					add(oldNode.getInfo());
			}
			return true;
		}
		return false;
	}

	/**
	 * Redispersiona la tabla Hash en otra del mismo tamano si el ratio de elementos
	 * borrados es mayor que 0.5
	 * 
	 * @return Se redisperso la tabla?
	 */
	@SuppressWarnings("unchecked")
	protected boolean deletedReDispersion() {
		if (getDeletedRate() > 0.5) {
			HashNode<T>[] oldAssociativeArray = this.associativeArray;
			this.associativeArray = (HashNode<T>[]) new HashNode[this.hashSize];
			for (int i = 0; i < this.hashSize; i++)
				this.associativeArray[i] = new HashNode<T>();
			this.numElems = 0;

			for (int i = 0; i < this.hashSize; i++) {
				HashNode<T> oldNode = oldAssociativeArray[i];
				if (oldNode.getStatus() == HashNode.LLENO)
					add(oldNode.getInfo());
			}
			return true;
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder cadena = new StringBuilder();
		for (int i = 0; i < getSize(); i++) {
			cadena.append(associativeArray[i]);
			cadena.append(";");
		}
		cadena.append("[Size: ");
		cadena.append(getSize());
		cadena.append(" Num.Elems.: ");
		cadena.append(getNumOfElems());
		cadena.append("]");
		return cadena.toString();
	}

	/**
	 * Calcula y devuelve el factor de carga
	 * 
	 * @return Factor de carga
	 */
	protected double getFC() {
		return ((new Double(this.numElems)).doubleValue() / (new Double(this.hashSize)).doubleValue());
	}

	/**
	 * Devuelve la posicion candidata en base al tipo de exploracion configurado en
	 * la tabla Hash
	 * 
	 * @param informacion Informacion con la que se explora
	 * @param iteracion   Iteracion de la exploracion
	 * @return Posicion candidata
	 */
	protected int fExploracion(T informacion, int iteracion) {
		switch (this.exploracion) {
		case CUADRATICA:
			return (fHash(informacion) + iteracion * iteracion) % getSize();
		case DOBLEHASH:
			return (fHash(informacion) + iteracion * fDispersion(informacion)) % getSize();
		default:
			return (fHash(informacion) + iteracion) % getSize();
		}
	}

	/**
	 * Evalua la funcion de dispersion para la exploracion doble Hash dada una
	 * informacion.
	 * 
	 * @param informacion Informacion con la que evaluar
	 * @return Valor de dispersion evaluado
	 */
	protected int fDispersion(T informacion) {
		int R = previousPrimeNumber(getSize());
		int positiveValue = informacion.hashCode() % R;
		if (positiveValue < 0)
			positiveValue += R;
		return R - positiveValue;
	}

	/**
	 * Establece el tipo de exploracion, evitando que se establezcan tipos
	 * inexistentes. Si se intenta establecer una exploracion que no existe, se
	 * establece lineal.
	 * 
	 * @param exploracion Exploracion a establecer
	 */
	protected void setExploracion(int exploracion) {
		this.exploracion = (exploracion != LINEAL && exploracion != CUADRATICA && exploracion != DOBLEHASH) ? LINEAL
				: exploracion;
	}
}
