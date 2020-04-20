package com.raines.raineslearn.async.chapter3.FutureTask;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Optional
 */
public class Demo {

    public static AtomicReference<String> main(String[] args) {
        Optional<String> found = find(true);
        AtomicReference<String> result = new AtomicReference<>("");
        found.ifPresent(content -> {
            result.set(content);    // can't assign to result
        });
        return result;
    }

    private static Optional<String> find(boolean flag){
        if (flag){
            return Optional.empty();
        }
        return Optional.ofNullable("sdf");
    }

}
