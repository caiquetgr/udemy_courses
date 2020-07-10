package com.activemesa.patterns.behavioral.interpreter;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

interface Element {
    int eval();
}

@AllArgsConstructor
class Integer implements Element {

    private int value;

    @Override
    public int eval() {
        return value;
    }
}

class BinaryOperation implements Element {

    public enum Type {
        ADDITION,
        SUBTRACTION;
    }

    public Type type;
    public Element left, right;

    @Override
    public int eval() {

        switch (type) {
            case ADDITION:
                return left.eval() + right.eval();
            case SUBTRACTION:
                return left.eval() - right.eval();
            default:
                return 0;
        }

    }

}

@Getter
@AllArgsConstructor
class Token {
    public enum Type {
        INTEGER,
        PLUS,
        MINUS,
        LPAREN,
        RPAREN
    }

    private Type type;
    private String text;

    @Override
    public String toString() {
        return "`" + text + "`";
    }
}

public class Demo {

    private static Element parse(List<Token> tokens) {

        BinaryOperation binaryOperation = new BinaryOperation();
        boolean haveLHS = false;

        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);

            switch (token.getType()) {
                case INTEGER:
                    Integer integer = new Integer(java.lang.Integer.parseInt(token.getText()));
                    if (!haveLHS) {
                        binaryOperation.left = integer;
                        haveLHS = true;
                    } else binaryOperation.right = integer;
                    break;
                case PLUS:
                    binaryOperation.type = BinaryOperation.Type.ADDITION;
                    break;
                case MINUS:
                    binaryOperation.type = BinaryOperation.Type.SUBTRACTION;
                    break;
                case LPAREN:
                    int j = i; // location of RPAREN
                    for (; j < tokens.size(); ++j)
                        if (Token.Type.RPAREN.equals(tokens.get(j).getType()))
                            break;
                    List<Token> subexpression = tokens.stream()
                            .skip(i + 1)
                            .limit(j-i-1)
                            .collect(Collectors.toList());
                    Element element = parse(subexpression);
                    if (!haveLHS) {
                        binaryOperation.left = element;
                        haveLHS = true;
                    } else binaryOperation.right = element;
                    i = j;
                    break;
            }
        }

        return binaryOperation;

    }

    private static List<Token> lex(String input) {

        List<Token> tokens = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {

            char ch = input.charAt(i);

            switch (ch) {
                case '+':
                    tokens.add(new Token(Token.Type.PLUS, "+"));
                    break;
                case '-':
                    tokens.add(new Token(Token.Type.MINUS, "-"));
                    break;
                case '(':
                    tokens.add(new Token(Token.Type.LPAREN, "("));
                    break;
                case ')':
                    tokens.add(new Token(Token.Type.RPAREN, ")"));
                    break;
                default:
                    if (Character.isDigit(ch)) {
                        StringBuilder sb = new StringBuilder("" + ch);
                        for (int j = i + 1; j < input.length(); j++) {
                            char chJ = input.charAt(j);
                            if (Character.isDigit(chJ)) {
                                sb.append(chJ);
                                ++i;
                            } else {
                                tokens.add(new Token(Token.Type.INTEGER, sb.toString()));
                                break;
                            }
                        }
                    }
                    break;
            }
        }

        return tokens;

    }

    public static void main(String[] args) {

        String input = "(13+4)-(12+1)";
        List<Token> tokens = lex(input);

        tokens.forEach(System.out::println);
        System.out.println(input + " = " + parse(tokens).eval());

    }


}
