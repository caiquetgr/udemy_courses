package com.activemesa.behavioral.chainofresponsabilty.methodchain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class Creature1 {

    private String name;
    private int attack, defense;

}

class CreatureModifier {

    protected Creature1 creature;
    protected CreatureModifier next;

    public CreatureModifier(Creature1 creature) {
        this.creature = creature;
    }

    public void setNext(CreatureModifier creatureModifier) {
        if (next != null) {
            next.setNext(creatureModifier);
        } else {
            this.next = creatureModifier;
        }
    }

    public void handle() {
        if (next != null) {
            next.handle();
        }
    }

}

class DoubleAttackModifier extends CreatureModifier {

    public DoubleAttackModifier(Creature1 creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("Doubling " + creature.getName() + "'s attack");
        creature.setAttack(creature.getAttack() * 2);
        super.handle();
    }

}

class IncreaseDefenseModifier extends CreatureModifier {

    public IncreaseDefenseModifier(Creature1 creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("Increasing " + creature.getName() + "'s defense");
        creature.setDefense(creature.getDefense() + 3);
        super.handle();
    }

}

class NoBonusesModifier extends CreatureModifier {

    public NoBonusesModifier(Creature1 creature) {
        super(creature);
    }

    @Override
    public void handle() {
        System.out.println("No bonuses for you!");
    }

}

public class CreatureMethodChain {
    public static void main(String[] args) {

        Creature1 goblin = new Creature1("goblin", 2, 2);
        System.out.println(goblin);

        CreatureModifier creatureModifier = new CreatureModifier(goblin);

        //creatureModifier.setNext(new NoBonusesModifier(goblin));
        creatureModifier.setNext(new DoubleAttackModifier(goblin));
        creatureModifier.setNext(new IncreaseDefenseModifier(goblin));

        creatureModifier.handle();

        System.out.println(goblin);

    }
}
