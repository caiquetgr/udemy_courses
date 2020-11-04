package br.com.caiqueborges;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Waiting input to start...");
        System.in.read();
        System.out.println("Started");

        List<Customer> customers = new ArrayList<>();

        while (true) {
            Customer customer = new Customer("Caique");
            customers.add(customer);

            if (customers.size() > 10000) {
                for (int i = 0; i < 5000; i++) {
                    customers.remove(0);
                }
            }

        }

    }

}
