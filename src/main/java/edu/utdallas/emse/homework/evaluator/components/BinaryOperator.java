package edu.utdallas.emse.homework.evaluator.components;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum BinaryOperator {
    SHIFT(0, 1), ADD(1, 1), SUBTRACT(1, -1);

    private int leftCoefficient;
    private int rightCoefficient;
    private static final Map<Character, BinaryOperator> symbolToEnum = new ConcurrentHashMap<>();

    static {
        symbolToEnum.put('+', ADD);
        symbolToEnum.put('-', SUBTRACT);
    }

    BinaryOperator(int leftCo, int rightCo) {
        this.leftCoefficient = leftCo;
        this.rightCoefficient = rightCo;
    }

    public int operate(int left, int right) {
        return this.leftCoefficient * left + this.rightCoefficient * right;
    }

    public static BinaryOperator getOperator(char symbol) {
        BinaryOperator retVal = symbolToEnum.get(symbol);

        return retVal == null ? SHIFT : retVal;
    }
}
