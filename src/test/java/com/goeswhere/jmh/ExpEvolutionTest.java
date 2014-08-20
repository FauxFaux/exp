package com.goeswhere.jmh;

public class ExpEvolutionTest {
    public static void main(String[] args) {
        final ExpEvolution ut = new ExpEvolution();
        double diff = 0;
        long count = 0;
        for (double i = 0; i < 1; i += 0.0000001) {
            ++count;
            ut.value = i;
            diff += square(ut.fastMath() - ut.approximation5());
        }

        System.out.println(diff / count);
    }

    private static double square(double v) {
        return v*v;
    }
}