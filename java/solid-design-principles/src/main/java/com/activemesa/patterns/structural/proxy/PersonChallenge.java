package com.activemesa.patterns.structural.proxy;

public class PersonChallenge {
    private int age;

    public PersonChallenge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String drink() {
        return "drinking";
    }

    public String drive() {
        return "driving";
    }

    public String drinkAndDrive() {
        return "driving while drunk";
    }
}

class ResponsiblePerson {
    private PersonChallenge personChallenge;

    public ResponsiblePerson(PersonChallenge personChallenge) {
        // todo
        this.personChallenge = personChallenge;
    }

    public int getAge() {
        return personChallenge.getAge();
    }

    public void setAge(int age) {
        personChallenge.setAge(age);
    }

    public String drink() {
        if (personChallenge.getAge() < 18) {
            return "too young";
        }
        return personChallenge.drink();
    }

    public String drive() {
        if (personChallenge.getAge() < 16) {
            return "too young";
        }
        return personChallenge.drive();
    }

    public String drinkAndDrive() {
        return "dead";
    }
}
