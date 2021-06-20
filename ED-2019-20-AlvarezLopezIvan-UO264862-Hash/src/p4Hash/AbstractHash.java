package p4Hash;

/**
 * Clase abstracta AbstractHash la cual se debe extender para implementar
 * nuestra clase ClosedHashTable.
 * 
 * @author Nestor Garcia Fernandez
 * @version Curso 2019 - 2020
 * @param <T> Tipo generico
 */
public abstract class AbstractHash<T> {

	/**
	 * Devuelve el numero de elementos que contiene la tabla Hash en el momento de
	 * la invocacion
	 * 
	 * @return Cantidad de elementos de la tabla Hash
	 */
	abstract public int getNumOfElems();

	/**
	 * Devuelve el tamano de la tabla Hash
	 * 
	 * @return Capacidad de la tabla Hash
	 */
	abstract public int getSize();

	/**
	 * Inserta un nuevo elemento pasado como parametro en la tabla Hash.
	 * 
	 * @param elem Elemento a insertar
	 * @return 0 si se ha insertado el elemento; -1 si no encuentra sitio; -2 si el
	 *         elemento no es valido.
	 */
	abstract public int add(T elem);

	/**
	 * Busca un elemento pasado como parametro en la tabla Hash.
	 * 
	 * @param elem Elemento a encontrar
	 * @return Primer elemento coincidente encontrado; null si no lo encuentra.
	 */
	abstract public T find(T elem);

	/**
	 * Borra un elemento coincidente con el que se le pasa como parametro de la
	 * tabla Hash.
	 * 
	 * @param elem Elemento a eliminar
	 * @return 0 si lo ha borrado; -1 si no lo encuentra; -2 en otro caso.
	 */
	abstract public int remove(T elem);

	/**
	 * Realiza una redispersion (aumentando el tamaño) al numero primo mayor que el
	 * doble del tamaño actual, recolocando los elementos.
	 * 
	 * @return true si se ejecuta la redispersion; false si no lo hace.
	 */
	abstract protected boolean reDispersion();

	/**
	 * Realiza una redispersion inversa (disminuyendo el tamaño) al número primo
	 * menor que la mitad del tamaño actual, recolocando los elementos.
	 * 
	 * @return true si se ejecuta la redispersion inversa; false si no lo hace.
	 */
	abstract protected boolean inverseReDispersion();

	/**
	 * Convierte la tabla Hash a una String que se pueda mostrar de forma "visible"
	 * 
	 * @return Representacion grafica de la tabla Hash.
	 */
	abstract public String toString();

	/**
	 * Calcula el resultado de aplicar la funcion hash basada en modulo, sobre el
	 * parametro. Utiliza hashCode() y convierte posibles negativos a positivos.
	 * 
	 * @return Resultado de la funcion Hash
	 */
	protected int fHash(T elem) { // fHash basada en MODULO...
		int position = elem.hashCode() % getSize();
		return position < 0 ? position + getSize() : position;
	}

	/**
	 * 
	 * Calcula si un numero positivo es un numero primo, los negativos devuelve que
	 * no
	 * 
	 * @param n El numero que queremos comprobar
	 * @return true si es primo; false en caso contrario
	 */
	protected boolean isPositivePrime(int n) {
		if (n < 2 || (n > 2 && n % 2 == 0))
			return false;
		if (n <= 7)
			return true;
		for (int i = 3; i * i <= n; i += 2) // impares
			if (n % i == 0)
				return false; // no es primo
		return true;
	}

	/**
	 * Devuelve el mismo numero pasado como parametro si este es primo, sino
	 * devuelve el siguiente primo mayor.
	 * 
	 * @param n El numero de partida
	 * @return Si el numero es primo, el mismo numero; y sino, el primer primo
	 *         encontrado MAYOR que el numero de partida
	 */
	protected int nextPrimeNumber(int n) {
		if (n <= 3)
			return 3; // No permite primos menores de 3
		for (; !isPositivePrime(n); n++)
			;
		return n;
	}

	/**
	 * Devuelve el mismo numero pasado como parametro si este es primo, sino
	 * devuelve el anterior primo menor.
	 * 
	 * @param n El numero de partida
	 * @return Si el numero es primo, el mismo numero; y sino, el primer primo
	 *         encontrado MENOR que el numero de partida
	 */
	protected int previousPrimeNumber(int n) {
		if (n <= 3) // No permite primos menores de 3
			return 3;
		for (; !isPositivePrime(n); n--)
			;
		return n;
	}

}
