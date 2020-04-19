package com.activemesa.patterns.creational.factory;

class Person {
    public int id;
    public String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class PersonFactory {
    private static int idIndex = 0;

    public Person createPerson(String name) {
        return new Person(idIndex++, name);
    }
}

public class FactoryDemo {

    public static void main(String[] args) {
        PersonFactory personFactory = new PersonFactory();
      Person person = personFactory.createPerson("Caique");
      Person person2 = personFactory.createPerson("Caique");
      Person person3 = personFactory.createPerson("Caique");
      System.out.println(person);
      System.out.println(person2);
      System.out.println(person3);
    }

}


