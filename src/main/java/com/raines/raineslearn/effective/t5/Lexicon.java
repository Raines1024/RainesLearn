package com.raines.raineslearn.effective.t5;

import java.util.function.Supplier;

public interface Lexicon extends Supplier<Lexicon> {

    boolean check(String str);

}
