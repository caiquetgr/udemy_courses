package com.activemesa.patterns.behavioral.chainofresponsabilty;

import java.util.ArrayList;
import java.util.List;

abstract class Creature {
    public abstract int getAttack();

    public abstract int getDefense();
}

class Goblin extends Creature {

    private Game game;

    public Goblin(Game game) {
        this.game = game;
    }

    @Override
    public int getAttack() {

        int attack = 1;

        attack += game.creatures.stream()
                .filter(creature -> !creature.equals(this))
                .map(Object::getClass)
                .filter(GoblinKing.class::isAssignableFrom)
                .count();

        return attack;

    }

    @Override
    public int getDefense() {
        int defense = 1;

        defense += game.creatures.stream()
                .filter(creature -> !creature.equals(this))
                .map(Object::getClass)
                .filter(Goblin.class::isAssignableFrom)
                .count();

        return defense;
    }
}

class GoblinKing extends Goblin {
    public GoblinKing(Game game) {
        super(game);
    }

    @Override
    public int getAttack() {
        return super.getAttack() + 2;
    }

    @Override
    public int getDefense() {
        return super.getDefense() + 2;
    }
}

enum Statistic {
    ATTACK, DEFENSE
}

class Game {
    public List<Creature> creatures = new ArrayList<>();
}
