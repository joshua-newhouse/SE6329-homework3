package edu.utdallas.emse.homework.evaluator.components;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum BinaryOperator {
    ADD((left, right) -> left + right),
    SUBTRACT((left, right) -> left - right),
    LEFT_SHIFT((left, right) -> right);

    private static final Map<Character, BinaryOperator> SYMBOL_TO_ENUM;
    private BinaryFunction func;

    static {
        Map<Character, BinaryOperator> tmp = new ConcurrentHashMap<>();
        tmp.put('+', ADD);
        tmp.put('-', SUBTRACT);
        tmp.put((char) 0, LEFT_SHIFT);
        SYMBOL_TO_ENUM = Collections.unmodifiableMap(tmp);
    }

    BinaryOperator(BinaryFunction f) {
        this.func = f;
    }

    @FunctionalInterface
    private interface BinaryFunction {
        int getResult(int left, int right);
    }

    public static BinaryOperator getDefaultOperator() {
        return LEFT_SHIFT;
    }

    public static BinaryOperator getOperator(char op) {
        return SYMBOL_TO_ENUM.get(op);
    }

    public int operate(int left, int right) {
        return func.getResult(left, right);
    }
}