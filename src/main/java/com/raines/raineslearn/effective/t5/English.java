package com.raines.raineslearn.effective.t5;

import java.util.Objects;
import java.util.function.Supplier;

public class English  implements Lexicon {

    @Override
    public English get() {
        System.out.println("English Get");
        return null;
    }

    @Override
    public boolean check(String eng) {
        System.out.println("English  check走了");
        return Objects.nonNull(eng);
    }
}
