package com.raines.raineslearn.demo;

public class TestStringReplace {

    private static String replace(String src, String pattern, String replacement) {
        int i = src.indexOf(pattern);
        if (i >= 0) {
            int ptnLen = pattern.length();
            StringBuilder sb = new StringBuilder(src.length());
            sb.append(src, 0, i).append(replacement);
            i += ptnLen;
            for (;;) {
                int j = src.indexOf(pattern, i);
                if (j >= 0) {
                    sb.append(src, i, j).append(replacement);
                    i = j + ptnLen;
                } else {
                    return sb.append(src, i, src.length()).toString();
                }
            }
        } else {
            return src;
        }
    }

    private static final int LOOPS = 100 * 10000;
    private static final String SRC = "asdfasdfassdd2edsdfasdfasdfasdfasdfasdfasdsdfasdfasdfasdfasdfasdfasdsdfasdfasdfasdfsd";
    private static final String PATTERN = "sdf";
    private static final String REP = "ijkk2eeew;";

    public static void main(String[] args) {
        System.out.println(replace(SRC, PATTERN, REP));
        System.out.println(replace(SRC, PATTERN, REP).equals(SRC.replace(PATTERN, REP)));

        System.out.println("test replace1---------------------------------------");
        timeit(() -> {
            for (int i = 0; i < LOOPS; i++) {
                replace(SRC, PATTERN, REP);
            }
        }, 6);

        System.out.println("test string.replace-------------------------------------");
        timeit(() -> {
            for (int i = 0; i < LOOPS; i++) {
                SRC.replace(PATTERN, REP);
            }
        }, 6);
    }

    private static void timeit(Runnable r, int times) {
        long total = 0;
        for (int i = 0; i < times; i++) {
            long start = System.currentTimeMillis();
            r.run();
            long end = System.currentTimeMillis();
            total += end - start;
            System.out.println(end - start);
        }
        System.out.println("AVG " + total / times);
    }

}