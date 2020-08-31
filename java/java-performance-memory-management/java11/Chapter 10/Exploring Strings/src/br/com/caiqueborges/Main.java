package br.com.caiqueborges;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<String> strings = new ArrayList<>();

        Instant start = Instant.now();

        for (Integer i = 0; i < 10000000; i++) {
            String s = i.toString().intern();
            strings.add(s);
        }

        Instant end = Instant.now();

        System.out.println("Duração: " + Duration.between(start, end).toString());

    }
}
