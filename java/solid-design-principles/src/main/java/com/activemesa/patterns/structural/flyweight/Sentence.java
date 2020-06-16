package com.activemesa.patterns.structural.flyweight;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

// Exercise
class Sentence {
    private String[] splitedText;
    private String plainText;
    private List<WordToken> wordTokens;

    public Sentence(String plainText) {
        // todo
        this.splitedText = plainText.split(" ");
        this.plainText = plainText;
        this.wordTokens = new ArrayList<>(splitedText.length);

        IntStream.range(0, splitedText.length).forEach(i -> wordTokens.add(new WordToken()));

    }

    public WordToken getWord(int index) {
        // todo
        return this.wordTokens.get(index);
    }

    @Override
    public String toString() {

        for (int i = 0; i < splitedText.length; i++) {
            String word = splitedText[i];
            if (wordTokens.get(i).capitalize) {
                plainText = plainText.replace(word, word.toUpperCase());
            }
        }

        return plainText;

    }

    class WordToken {
        public boolean capitalize;
    }
}
