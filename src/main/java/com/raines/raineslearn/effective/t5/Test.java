package com.raines.raineslearn.effective.t5;

public class Test {

    public static void main(String[] args) {
        SpellChecker spellChecker = new SpellChecker(new English());
        spellChecker.isValid("w");
        spellChecker = new SpellChecker(new Chinese());
        spellChecker.isValid("w");
    }

}
