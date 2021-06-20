package p1Algoritmia;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.BeforeClass;
import org.junit.Test;

public class AlgorithmsTest {

	private static Algorithms al;

	@BeforeClass
	public static void initialiceAlgorithms() {
		al = new Algorithms();
	}

	@Test
	public void textX() {
		al.factorial(10000);
	}

	@Test
	public void timeMeasurement() {
		int n = 500;
		long startTime = System.currentTimeMillis();
		al.logarithmic(n);
		long deltaTime = System.currentTimeMillis() - startTime;
		System.out.println(deltaTime + ";" + 0);
	}

	@Test
	public void testFactorial() {
		// for (int i = 0; i <= 20; i++)
		// System.out.println("Factorial de "+i+": "+al.factorial(i));
		assertEquals(1, al.factorial(0));
		assertEquals(1, al.factorial(1));
		assertEquals(2, al.factorial(2));
		assertEquals(6, al.factorial(3));
		assertEquals(24, al.factorial(4));
		assertEquals(120, al.factorial(5));
		assertEquals(720, al.factorial(6));
		assertEquals(5040, al.factorial(7));
		assertEquals(40320, al.factorial(8));
		assertEquals(362880, al.factorial(9));
	}

	@Test
	public void testFib() {
		// for (int i = 0; i <= 12; i++)
		// System.out.println("Número "+i+" de Fibonacci: " + al.fibonacci(i));
		assertEquals(0, al.fibonacci(0));
		assertEquals(1, al.fibonacci(1));
		assertEquals(1, al.fibonacci(2));
		assertEquals(2, al.fibonacci(3));
		assertEquals(3, al.fibonacci(4));
		assertEquals(5, al.fibonacci(5));
		assertEquals(8, al.fibonacci(6));
		assertEquals(13, al.fibonacci(7));
		assertEquals(21, al.fibonacci(8));
		assertEquals(34, al.fibonacci(9));
	}

	@Test
	public void testPow2Rec1() {
		for (int i = 0; i <= 50; i++)
			assertEquals(Math.pow(2, i), al.pow2Rec1(i), 0.00001);
	}

	@Test
	public void testPow2Rec2() {
		for (int i = 0; i <= 50; i++)
			assertEquals(Math.pow(2, i), al.pow2Rec2(i), 0.00001);
	}

	@Test
	public void testPow2Rec3() {
		for (int i = 0; i <= 50; i++) {
			System.out.println(al.pow2Rec3(i));
			assertEquals(Math.pow(2, i), al.pow2Rec3(i), 0.00001);
		}
	}

	@Test
	public void testPotenciaRec() {
		for (int i = 1; i <= 30; i++)
			for (int j = 0; j <= 10; j++)
				assertEquals(Math.pow(i, j), al.potenciaRec(i, j), 0.00001);
	}

	@Test
	public void testInvertirEnteroRec() {
		Random r = new Random();
		String nums = "123456789";
		for (int i = 0; i < 10000; i++) {
			String derecho = "";
			String invertido = "";
			for (int j = 0; j < r.nextInt(8) + 1; j++) {
				char nextNum = nums.charAt(r.nextInt(nums.length()));
				derecho = nextNum + derecho;
				invertido = invertido + nextNum;
			}
			if (invertido.length() > 0 && derecho.length() > 0)
				assertEquals(Integer.valueOf(invertido).intValue(),
						al.invertirEnteroRec(Integer.valueOf(derecho).intValue()));
		}
	}

	@Test
	public void testInvertirString() {
		Random r = new Random();
		String alphaNumChars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < 10000; i++) {
			String derecho = "";
			String invertido = "";
			for (int j = 0; j < r.nextInt(12); j++) {
				char nextChar = alphaNumChars.charAt(r.nextInt(alphaNumChars.length()));
				derecho = nextChar + derecho;
				invertido = invertido + nextChar;
			}
			assertEquals(invertido, al.invertirStringRec(derecho));
		}
	}

	@Test
	public void testRestoDivRec() {
		for (int i = 0; i < 1000; i++) {
			for (int j = 1; j < 1000; j++)
				assertEquals(i % j, al.restoDivRec(i, j));
		}
	}

	@Test
	public void testEsSimetrica() {
		Random r = new Random();
		assertTrue(al.esSimetrica(new int[][] { { 1, 2 }, { 2, 1 } }));
		assertTrue(al.esSimetrica(new int[][] { { 1, 2, 3 }, { 2, 1, 2 }, { 3, 2, 1 } }));
		for (int a = 0; a < 100; a++) {
			int index = r.nextInt(20) + 1;
			int[][] matrix = new int[index][index];
			for (int i = 0; i < index; i++) {
				for (int j = 0; j < index; j++) {
					int value = r.nextInt(10);
					matrix[i][j] = value;
					matrix[j][i] = value;
				}
			}
			assertTrue(al.esSimetrica(matrix));
		}
		for (int a = 0; a < 100; a++) {
			int index = r.nextInt(20) + 2;
			int[][] matrix = new int[index][index];
			for (int i = 0; i < index; i++) {
				for (int j = 0; j < index; j++) {
					int value = r.nextInt(10);
					matrix[i][j] = value;
					matrix[j][i] = value;
				}
			}
			matrix[index - 1][index - 2] = 0;
			matrix[index - 2][index - 1] = 1;
			assertFalse(al.esSimetrica(matrix));
		}
	}

	@Test
	public void testHanoi() {
		al.hanoi(5);
		/*
		 * 
		 * 1 2 3 _ _ _
		 * 
		 * 
		 * 
		 * 2 3 1 _ _ _
		 * 
		 * 
		 * 
		 * 
		 * 3 2 1 _ _ _
		 * 
		 * 
		 * 
		 * 1 3 2 _ _ _
		 * 
		 * 
		 * 
		 * 1 2 3 _ _ _
		 * 
		 * 
		 * 
		 * 
		 * 1 2 3 _ _ _
		 * 
		 * 
		 * 
		 * 2 1 3 _ _ _
		 * 
		 * 
		 * 1 2 3 _ _ _
		 * 
		 * 
		 */
	}

}