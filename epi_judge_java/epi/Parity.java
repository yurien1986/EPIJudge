package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

public class Parity {
	@EpiTest(testDataFile = "parity.tsv")
	public static short parity(long x) {
		return Long.bitCount(x) % 2 == 0 ? (short) 0 : 1;
	}

	public static void main(String[] args) {
		System.exit(GenericTest.runFromAnnotations(args, "Parity.java", new Object() {
		}.getClass().getEnclosingClass()).ordinal());
	}
}
