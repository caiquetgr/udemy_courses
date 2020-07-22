package br.com.caiqueborges.section9;

import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceStringExample {

    public static void main(String[] args) {

        String oldName = "old name";
        String newName = "new name";

        AtomicReference<String> atomicString = new AtomicReference<>(oldName);

        atomicString.set("oi");
        
        if (atomicString.compareAndSet(oldName, newName)) {
            System.out.println("New value is " + atomicString.get());
        } else {
            System.out.println("Nothing changed");
        }

    }

}
