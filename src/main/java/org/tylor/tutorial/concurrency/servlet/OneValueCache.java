package org.tylor.tutorial.concurrency.servlet;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * Tylor 2020/11/7
 */
@Immutable
public class OneValueCache {
    private final BigInteger lastNumber;
    private  final BigInteger[] lastFactors;

    public OneValueCache(BigInteger lastNumber,BigInteger[] lastFactors){
        this.lastNumber = lastNumber;
        this.lastFactors = Arrays.copyOf(lastFactors, lastFactors.length);
    }
    public BigInteger[] getFactors (BigInteger i ){
        if (lastNumber == null||!lastNumber.equals(i)){
            return null;
        }else {
            return Arrays.copyOf(lastFactors, lastFactors.length);
        }
    }
}
