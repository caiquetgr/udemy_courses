package main;

public class NumberChecker {

	public Boolean isPrime(int testNumber) {
		for (int i = 2; i < testNumber; i++) {
			if (testNumber % i == 0) return false;
		}
		return true;
	}
	
	
}
