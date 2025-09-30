package com.example;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.Random;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class IfVsTernaryVsMathMax {

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(IfVsTernaryVsMathMax.class.getSimpleName())
                .forks(1)      // fork 1 次 JVM
                .warmupIterations(5)
                .measurementIterations(10)
                .build();

        new Runner(opt).run();
    }

    private int a;
    private int b;
    private static final int LOOP = 10_000_000; // 每次测试循环次数

    @Setup(Level.Iteration)
    public void setup() {
        Random rand = new Random(42);
        a = rand.nextInt();
        b = rand.nextInt();
    }

    @Benchmark
    public int testIf() {
        int sum = 0;
        for (int i = 0; i < LOOP; i++) {
            if (a > b) {
                sum += a;
            } else {
                sum += b;
            }
        }
        return sum;
    }

    @Benchmark
    public int testTernary() {
        int sum = 0;
        for (int i = 0; i < LOOP; i++) {
            sum += (a > b ? a : b);
        }
        return sum;
    }

    @Benchmark
    public int testMathMax() {
        int sum = 0;
        for (int i = 0; i < LOOP; i++) {
            sum += Math.max(a, b);
        }
        return sum;
    }
}
