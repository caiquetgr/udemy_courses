package com.activemesa.patterns.structural.decorator;

public class DecoratorExercise {
}

class Bird {
    public int age;

    public String fly() {
        return age < 10 ? "flying" : "too old";
    }
}

class Lizard {
    public int age;

    public String crawl() {
        return (age > 1) ? "crawling" : "too young";
    }
}

class Dragon {

    private final Bird bird;
    private final Lizard lizard;
    private int age;

    public Dragon() {
        this.bird = new Bird();
        this.lizard = new Lizard();
    }

    public Dragon(Bird bird, Lizard lizard) {
        this.bird = bird;
        this.lizard = lizard;
    }

    public void setAge(int age) {
        this.bird.age = age;
        this.lizard.age = age;
        this.age = age;
    }

    public String fly() {
        return bird.fly();
    }

    public String crawl() {
        return lizard.crawl();
    }
    
}
