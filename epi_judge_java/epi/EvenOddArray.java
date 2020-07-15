package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import epi.test_framework.TestFailure;
import epi.test_framework.TimedExecutor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EvenOddArray {

	public static void evenOdd(List<Integer> A) {

		int length = A.size();
		for (int i = 0; i < length; i++) {
			int current = A.get(i);
			if (!isEven(current)) {

				int start = i + 1;
				while (start < length) {
					if (isEven(A.get(start))) {
						Collections.swap(A, i, start);
						break;
					}
					start++;
				}
			}
		}
		return;
	}

	private static boolean isEven(int i) {
		return i % 2 == 0;
	}

	@EpiTest(testDataFile = "even_odd_array.tsv")
	public static void evenOddWrapper(TimedExecutor executor, List<Integer> A) throws Exception {
		List<Integer> before = new ArrayList<>(A);
		executor.run(() -> evenOdd(A));

		boolean inOdd = false;
		for (int i = 0; i < A.size(); i++) {
			if (A.get(i) % 2 == 0) {
				if (inOdd) {
					throw new TestFailure("Even elements appear in odd part");
				}
			} else {
				inOdd = true;
			}
		}
		List<Integer> after = new ArrayList<>(A);
		Collections.sort(before);
		Collections.sort(after);
		if (!before.equals(after)) {
			throw new TestFailure("Elements mismatch");
		}
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "EvenOddArray.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
