package com.raines.raineslearn.effective.t5;

import java.util.Objects;

public class Chinese  implements Lexicon {

    @Override
    public Lexicon get() {
        return null;
    }

    @Override
    public boolean check(String eng) {
        System.out.println("Chinese  check走了");
        return Objects.nonNull(eng);
    }

}
