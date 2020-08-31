package main;

import java.time.Duration;
import java.time.Instant;

public class Main {

    public static void main(String[] args) {
        Instant start = Instant.now();
        PrimeNumbers primeNumbers = new PrimeNumbers();
        Integer max = Integer.parseInt(args[0]);
        primeNumbers.generateNumbers(max);
        Instant end = Instant.now();

        System.out.println("Application elapsed time: " + Duration.between(start, end).toString());
    }

}
