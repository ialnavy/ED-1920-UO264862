package p1Algoritmia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.BeforeClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

@SuppressWarnings("unused")
class AlgorithmsBenchmarkTest {
	private static AlgorithmsBenchmark AB;
	private static int TIMES;

	@BeforeAll
	static void initialiceAlgorithmsBenchmark() {
		AB = new AlgorithmsBenchmark();
		TIMES = 5;
	}

	@Test
	void test() {
		// - 1.5.
		testLogarithmic(); // 1 ms per thread sleep
		testLinear(); // 1 ms per thread sleep
		testFactorial(); // 1 ms per thread sleep
		testQuadratic(); // 1 ms per thread sleep
		testCubic(); // 1 ms per thread sleep
		testFibonacci(); // 1 ms per thread sleep

		// - 1.6.
		testPow2Rec1(); // 1 ms per thread sleep
		testPow2Rec2(); // 1 ms per thread sleep
		testPow2Rec3(); // 1 min per thread sleep
		testPow2Rec4(); // 1 min per thread sleep
		testPow2Iter(); // 5 s per thread sleep
	}

	@Test
	void testLogarithmic() {
		AB.testFinal("logarithmic.csv", 1, 500, TIMES, "p1Algoritmia.Algorithms", "logarithmic"); // 73 s per thread
	}

	@Test
	void testLinear() {
		AB.testFinal("linear.csv", 1, 500, TIMES, "p1Algoritmia.Algorithms", "linear"); // 11 min per thread sleep
	}

	@Test
	void testFactorial() {
		AB.testFinal("factorial.csv", 1, 500, TIMES, "p1Algoritmia.Algorithms", "factorial"); // 11 min per thread sleep
	}

	@Test
	void testQuadratic() {
		AB.testFinal("quadratic.csv", 1, 500, TIMES, "p1Algoritmia.Algorithms", "quadratic"); // 800 ms per thread sleep
	}

	@Test
	void testCubic() {
		AB.testFinal("cubic.csv", 1, 500, TIMES, "p1Algoritmia.Algorithms", "cubic"); // 80 ms per thread sleep
	}

	@Test
	void testFibonacci() {
		AB.testFinal("fibonacci.csv", 1, 500, TIMES, "p1Algoritmia.Algorithms", "fibonacci"); // 1 ms thread sleep
	}

	@Test
	void testPow2Rec1() {
		AB.testFinal("pow2Rec1.csv", 1, 31, TIMES, "p1Algoritmia.Algorithms", "pow2Rec1"); // 1 ms per thread sleep
	}

	@Test
	void testPow2Rec2() {
		AB.testFinal("pow2Rec2.csv", 1, 31, TIMES, "p1Algoritmia.Algorithms", "pow2Rec2"); // 1 ms per thread sleep
	}

	@Test
	void testPow2Rec3() {
		AB.testFinal("pow2Rec3.csv", 1, 31, TIMES, "p1Algoritmia.Algorithms", "pow2Rec3"); // 1 s per thread sleep
	}

	@Test
	void testPow2Rec4() {
		AB.testFinal("pow2Rec4.csv", 1, 31, TIMES, "p1Algoritmia.Algorithms", "pow2Rec4"); // 1 s per thread sleep
	}

	@Test
	void testPow2Iter() {
		AB.testFinal("pow2Iter.csv", 1, 31, TIMES, "p1Algoritmia.Algorithms", "pow2Iter"); // 1 ms per thread sleep
	}
}
