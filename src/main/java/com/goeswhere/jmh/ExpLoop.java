package com.goeswhere.jmh;

import org.apache.commons.math3.util.FastMath;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import sun.misc.Unsafe;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.DoubleUnaryOperator;

@State(Scope.Thread)
public class ExpLoop {
    public static final Unsafe UNSAFE;
    static {
        try {

            Constructor<Unsafe> unsafeConstructor = Unsafe.class.getDeclaredConstructor();
            unsafeConstructor.setAccessible(true);
            UNSAFE = unsafeConstructor.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    int items = 32;
    double[] value = new double[items];



    long ptr = UNSAFE.allocateMemory(items * 8);
    {
        for (int i = 0; i < value.length; ++i) {
            final double r = Math.random();
            value[i] = r;
            UNSAFE.putDouble(ptr + (i * 8), r);
        }
    }


    double mapSum(DoubleUnaryOperator function) {
        double acc = 0.0;
        for (double d : value) {
            acc += function.applyAsDouble(d);
        }
        return acc;
    }

//    @Benchmark
    public double fastMath() {
        return mapSum((d) ->  1.0 / (1 + FastMath.exp(-1.5 * d * d + 2)));
    }

//    @Benchmark
    public double approximation5() {
        return mapSum((d) -> {
            final double sqr = d * d;
            if (sqr >= 0 && sqr < 1) {
                return -0.005778650800243143 * sqr * sqr * sqr * sqr * sqr +
                        -0.006625025107647994 * sqr * sqr * sqr * sqr +
                        0.02384996217100889 * sqr * sqr * sqr +
                        0.08932782438172726 * sqr * sqr +
                        0.1575625754158511 * sqr +
                        0.11920104248220616;
            }
            return fastMath();
        });
    }

    @Benchmark
    public double jni() {
        return Exp.calculate(value);
    }

    @Benchmark
    public double jniPointer() {
        return Exp.calculatePointer(ptr, items);
    }
}
