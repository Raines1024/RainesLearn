package com.raines.raineslearn.interesting.autoloadJar;

import java.io.File;

public class Test {

    public static void main(String[] args) {
        try {
            Utils.loadJarsFromAppFolder("lib");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
