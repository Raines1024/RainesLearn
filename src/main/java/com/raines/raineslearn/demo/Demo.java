package com.raines.raineslearn.demo;

public class Demo {

    public static void main(String[] args) {
        System.out.println(getMemUsage());
    }

    public static String getMemUsage() {
        long free = java.lang.Runtime.getRuntime().freeMemory();
        long total = java.lang.Runtime.getRuntime().totalMemory();
        StringBuffer buf = new StringBuffer();
        buf.append("[Mem: used ").append((total-free)>>20)
                .append("M free ").append(free>>20)
                .append("M total ").append(total>>20).append("M]");
        return buf.toString();
    }

}
