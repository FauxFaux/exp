package com.goeswhere.jmh;

import org.apache.commons.math3.util.FastMath;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Thread)
public class ExpEvolution {

    double value = 0.123456789d;

    @Benchmark
    public double original() {
        return 1.0 / (1 + Math.exp(-1.5 * Math.pow(value, 2) + 2));
    }

    @Benchmark
    public double cleanJava() {
        return 1.0 / (1 + Math.exp(-1.5 * value * value + 2));
    }

    @Benchmark
    public double fastMath() {
        return 1.0 / (1 + FastMath.exp(-1.5 * value * value + 2));
    }

    @Benchmark
    public double approximation() {
        final double sqr = value * value;
        if (sqr >= 0 && sqr < 1) {
            // import math; import numpy as np;
            // g=lambda a:1/(1+math.exp(-1.5*a+2));
            // x = np.arange(0,1,0.0001); y = [g(a) for a in x];
            // c=np.polyfit(x, y, 2).tolist(); c
            return 0.1034274489106936 * sqr * sqr + 0.15550046971524753 * sqr + 0.11920662709562624;
        }
        return fastMath();
    }

    @Benchmark
    public double approximation5() {
        final double sqr = value * value;
        if (sqr >= 0 && sqr < 1) {
            return -0.005778650800243143 * sqr * sqr * sqr * sqr * sqr +
                    -0.006625025107647994 * sqr * sqr * sqr * sqr +
                    0.02384996217100889 * sqr * sqr * sqr +
                    0.08932782438172726 * sqr * sqr +
                    0.1575625754158511 * sqr +
                    0.11920104248220616;
        }
        return fastMath();
    }
}
