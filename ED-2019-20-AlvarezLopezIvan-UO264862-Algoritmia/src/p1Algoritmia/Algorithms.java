package p1Algoritmia;

public class Algorithms {
    private static final long SLEEP_TIME = 1;

    public static void doNothing(long sleepTime) {
	try {
	    Thread.sleep(sleepTime);
	} catch (InterruptedException e) {
	    e.printStackTrace();
	}
    }

    /**
     * Factorial de un n�mero entero positivo (n mayor o igual que 0). Recursi�n
     * lineal y directa. Complejidad espacial y temporal: O(n). - Caso base: fac(0)
     * = 1 - Caso general: fac(n) = n * fac(n-1)
     * 
     * @param n N�mero
     * @return Factorial del n�mero dado
     */
    public long factorial(int n) {
	if (n >= 0) {
	    // doNothing(SLEEP_TIME);
	    return n == 0 ? 1 : n * factorial(n - 1);
	} else
	    return -1;
    }

    /**
     * N�mero de Fibonacci en�simo (n mayor o igual que 0). Recursi�n no lineal y
     * directa. Complejidad espacial: O(n). Complejidad temporal: O(2^n). - Caso
     * base: fib(0) = 0, fib(1) = 1 - Caso general: fib(n) = fib(n-1) + fib(n-2)
     * 
     * @param n Posici�n del n�mero de Fibonacci a calcular
     * @return N�mero de Fibonacci calculado
     */
    public int fibonacci(int n) {
	if (n >= 0) {
	    if (n == 0)
		return 0;
	    else if (n == 1)
		return 1;
	    else {
		// doNothing(SLEEP_TIME);
		return fibonacci(n - 1) + fibonacci(n - 2);
	    }
	} else
	    return -1;
    }

    /**
     * Potencia en�sima de 2. Complejidad espacial y temporal: O(n). - Caso base:
     * pow2Rec1(0) = 1 - Caso general: pow2Rec1(n) = 2 * pow2Rec1(n - 1)
     * 
     * @param n Exponente
     * @return En�sima potencia de 2
     */
    public long pow2Rec1(int n) {
	if (n >= 0) {
	    // doNothing(SLEEP_TIME);
	    return n == 0 ? 1 : 2 * pow2Rec1(n - 1);
	} else
	    return -1;
    }

    /**
     * Potencia b-�sima de a. Complejidad espacial y temporal: O(n). - Caso base:
     * potenciaRec(a, 0) = 1 - Caso general: potenciaRec(a, b) = a * potenciaRec(a,
     * b - 1)
     * 
     * @param a Base
     * @param b Exponente
     * @return a^b
     */
    public long potenciaRec(int a, int b) {
	if (b < 0)
	    return 1 / potenciaRec(a, -b);
	else {
	    if (a == 0) {
		if (b == 0)
		    throw new IllegalArgumentException("Indeterminación");
		else
		    return 0;
	    } else if (a < 0) {
		if (b % 2 == 0)
		    return potenciaRec(-a, b);
		else
		    return -potenciaRec(-a, b);
	    } else
		return b == 0 ? 1 : a * potenciaRec(a, b - 1);
	}
    }

    /**
     * Resto de la divisi�n de los n�meros enteros positivos a y b. Complejidad
     * espacial y temporal: O(n). - Caso base: a menor que b, restoDivRec(a,b) = a -
     * Caso general: a mayor o igual que b, restoDivRec(a,b) = restoDivRec(a - b, b)
     * 
     * @param a Dividendo
     * @param b Divisor
     * @return a % b
     */
    public int restoDivRec(int a, int b) {
	if (a >= 0 && b >= 0)
	    return a < b ? a : restoDivRec(a - b, b);
	else
	    return -1;
    }

    /**
     * Inversi�n de un n�mero entero positivo. Complejidad espacial y temporal:
     * O(n). - Caso base: n menor que 10, invertirEnteroRec(n) = n - Caso general: n
     * mayor o igual que 10, (n % 10) * (10 ^ log10(n) + invertirEnteroRec(n / 10))
     * 
     * @param n Entero a invertir
     * @return n Entero invertido
     */
    public int invertirEnteroRec(int n) {
	if (n >= 0)
	    return n < 10 ? n : (n % 10) * ((int) Math.pow(10, (int) Math.log10(n))) + invertirEnteroRec(n / 10);
	else
	    return -1;
    }

    /**
     * Inversi�n de una cadena de caracteres. Complejidad espacial y temporal: O(n).
     * - Caso base: invertirStringRec("") = "" - Caso general: invertirStringRec(l)
     * = l.charAt(length - 1) + invertirStringRec(l.substring(0, length - 1))
     * 
     * @param l Cadena de caracteres
     * @return Cadena de caracteres invertida
     */
    public String invertirStringRec(String l) {
	int length = l.length();
	return length == 0 ? "" : l.charAt(length - 1) + invertirStringRec(l.substring(0, length - 1));
    }

    /**
     * Evaluaci�n de la simetr�a de una matriz cuadrada.
     * 
     * @param m Matriz a evaluar
     * @return �Es sim�trica?
     */
    public boolean esSimetrica(int[][] m) {
	return esSimetrica(m, 0, 0);
    }

    /**
     * M�todo auxiliar privado de 'esSimetrica(int[][] m)'. Complejidad espacial:
     * O(n) Complejidad temporal: O(n*log(n)) - Caso base: Se termina la matriz. -
     * Caso general: Se compara los n�meros de los extremos [i][j] y [j][i].
     * 
     * @param m Matriz a evaluar
     * @param i �ndice primero iterado
     * @param j �ndice segundo iterado
     * @return �Es sim�trica?
     */
    private boolean esSimetrica(int[][] m, int i, int j) {
	if (i >= m.length)
	    return true;
	else {
	    if (j >= m[i].length)
		return esSimetrica(m, i + 1, 0);
	    else
		return (m[i][j] == m[j][i]) && (esSimetrica(m, i, j + 1));
	}
    }

    /**
     * Juego de las Torres de Hanoi, simulado con estructuras de pila con n�meros
     * Complejidad: O(2^n) - Caso base: 1 disco, destination.push(origin.pop()) -
     * Caso general: n discos, hanoi(n - 1, origin, auxiliar, destination);
     * destination.push(origin.pop()); hanoi(n - 1, auxiliar, destination, origin);
     * 
     * @param n Cantidad de discos con los que jugar.
     */
    public void hanoi(int n) {
	Hanoi h = new Hanoi(n);
	h.run();
    }

    /**
     * Algoritmo que no hace nada de complejidad lineal.
     * 
     * @param n Entero
     */
    public void linear(int n) {
	for (int i = 1; i < n; i++)
	    doNothing(SLEEP_TIME);
    }

    /**
     * Algoritmo que no hace nada de complejidad cuadr�tica.
     * 
     * @param n Entero
     */
    public void quadratic(int n) {
	for (int i = 1; i < n * n; i++)
	    doNothing(SLEEP_TIME);
    }

    /**
     * Algoritmo que no hace nada de complejidad c�bica.
     * 
     * @param n Entero
     */
    public void cubic(int n) {
	for (int i = 1; i < n * n * n; i++)
	    doNothing(SLEEP_TIME);
    }

    /**
     * Algoritmo que no hace nada de complejidad logar�tmica.
     * 
     * @param n Entero
     */
    public void logarithmic(int n) {
	for (int i = 1; i < n; i *= 2)
	    doNothing(1000);
    }

    /**
     * Potencia en�sima de 2, versi�n 2. - Caso base: pow2Rec2(0) = 1 - Caso
     * general: pow2Rec2(n) = pow2Rec2(n - 1) + pow2Rec2(n - 1)
     * 
     * @param n Exponente
     * @return En�sima potencia de 2
     */
    public long pow2Rec2(int n) {
	if (n < 0)
	    return 1 / pow2Rec2(-n);
	else if (n == 0)
	    return 1;
	else {
	    // doNothing(SLEEP_TIME);
	    return pow2Rec2(n - 1) + pow2Rec2(n - 1);
	}
    }

    /**
     * Potencia en�sima de 2, versi�n 3. - Caso base: pow2Rec3(0) = 1 - Caso
     * general: pow2Rec3(n) = pow2Rec3(n / 2) * pow2Rec3(n / 2) [Con n par]
     * pow2Rec3(n) = 2 * pow2Rec3(n / 2) * pow2Rec3(n / 2) [Con n impar]
     * 
     * @param n Exponente
     * @return En�sima potencia de 2
     */
    public long pow2Rec3(int n) {
	if (n < 0)
	    return 1 / pow2Rec3(n);
	else if (n == 0)
	    return 1;
	else if (n % 2 != 0) {
	    // doNothing(1000);
	    return 2 * pow2Rec3((n - 1) / 2) * pow2Rec3((n - 1) / 2);
	} else {
	    // doNothing(1000);
	    return pow2Rec3(n / 2) * pow2Rec3(n / 2);
	}
    }

    /**
     * Potencia en�sima de 2, versi�n 4. Id�ntico a la versi�n 3, pero sin repetir
     * la llamada recursiva.
     * 
     * @param n Exponente
     * @return En�sima potencia de 2
     */
    public long pow2Rec4(int n) {
	if (n < 0)
	    return 1 / pow2Rec4(n);
	if (n == 0)
	    return 1;
	long value;
	if (n % 2 != 0) {
	    // doNothing(1000);
	    value = pow2Rec4((n - 1) / 2);
	    return 2 * value * value;
	} else {
	    // doNothing(1000);
	    value = pow2Rec3(n / 2);
	    return value * value;
	}
    }

    /**
     * Pot�ncia en�sima de 2, versi�n iterativa.
     * 
     * @param n Exponente
     * @return En�sima potencia de 2
     */
    public long pow2Iter(int n) {
	if (n == 0)
	    return 1;
	int sign = (n > 0) ? 1 : -1;
	long b = (long) Math.pow(2, sign);
	int i = (n > 0) ? 1 : -1;
	int increment = (n > 0) ? 1 : -1;
	while (i != n) {
	    // doNothing(SLEEP_TIME);
	    b *= (long) Math.pow(2, sign);
	    i += increment;
	}
	return b;
    }
}
