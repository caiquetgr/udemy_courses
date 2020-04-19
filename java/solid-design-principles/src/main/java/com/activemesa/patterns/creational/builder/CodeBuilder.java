package com.activemesa.patterns.creational.builder;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class CodeBuilder {

    private String className;
    private List<String> attributes;

    public CodeBuilder(String className) {
        this.className = className;
        attributes = new ArrayList<>();
    }

    public CodeBuilder addField(String name, String type) {
        this.attributes.add(String.format("%s %s", type, name));
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder()
                .append(MessageFormat.format("public class {0}", this.className))
                .append(System.lineSeparator())
                .append("{")
                .append(System.lineSeparator());

        for (String attribute : attributes) {
            sb.append(MessageFormat.format("  private {0};", attribute))
                    .append(System.lineSeparator());
        }

        sb.append("}");

        return sb.toString();

    }
}

class Test {

    public static void main(String[] args) {
        System.out.println(new CodeBuilder("Person")
                .addField("name", "String")
                .addField("age", "int"));
    }

}
