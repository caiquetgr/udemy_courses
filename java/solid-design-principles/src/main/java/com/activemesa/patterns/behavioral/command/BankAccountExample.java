package com.activemesa.patterns.behavioral.command;

import lombok.ToString;

import java.text.MessageFormat;
import java.util.List;

@ToString
class BankAccount {
    private int balance;
    private int overdraftLimit = -500;

    public void deposit(int amount) {
        balance += amount;
        System.out.println(MessageFormat.format("Deposited {0}, balance is now {1}", amount, balance));
    }

    public boolean withdraw(int amount) {
        if (balance - amount >= overdraftLimit) {
            balance -= amount;
            System.out.println(MessageFormat.format("Withdrew {0}, balance is now {1}", amount, balance));
            return true;
        }
        return false;
    }

}

interface Command {
    void call();

    void undo();
}

class BankAccountCommand implements Command {

    private BankAccount bankAccount;
    private Action action;
    private int amount;

    private boolean succeeded;

    public enum Action {
        DEPOSIT, WITHDRAW
    }

    public BankAccountCommand(BankAccount bankAccount, Action action, int amount) {
        this.bankAccount = bankAccount;
        this.action = action;
        this.amount = amount;
    }

    @Override
    public void call() {

        switch (action) {
            case DEPOSIT:
                bankAccount.deposit(amount);
                succeeded = true;
                break;
            case WITHDRAW:
                succeeded = bankAccount.withdraw(amount);
                break;
        }
    }

    @Override
    public void undo() {

        if (!succeeded) return;

        switch (action) {
            case DEPOSIT:
                bankAccount.withdraw(amount);
                break;
            case WITHDRAW:
                bankAccount.deposit(amount);
                break;
        }

    }
}

public class BankAccountExample {

    public static void main(String[] args) {

        BankAccount bankAccount = new BankAccount();
        System.out.println(bankAccount);

        List<BankAccountCommand> commands = List.of(
                new BankAccountCommand(bankAccount, BankAccountCommand.Action.DEPOSIT, 100),
                new BankAccountCommand(bankAccount, BankAccountCommand.Action.WITHDRAW, 1000)
        );

        commands.forEach(command -> {
            command.call();
            System.out.println(bankAccount);
        });

        commands.forEach(command -> {
            command.undo();
            System.out.println(bankAccount);
        });


    }

}
