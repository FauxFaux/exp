package com.goeswhere.jmh;

public class Exp {
    static {
        System.loadLibrary("exp");
    }

    public static native double calculate(double[] doubles);

    public static native double calculatePointer(long ptr, int length);
}
