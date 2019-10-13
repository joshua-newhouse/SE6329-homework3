package edu.utdallas.emse.homework.evaluator.components;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum BinaryOperator {
    ADD((left, right) -> left + right),
    SUBTRACT((left, right) -> left - right);

    private static final Map<Character, BinaryOperator> SYMBOL_TO_ENUM;
    private BinaryFunction func;
    private static final String AVAILABLE_OPS;

    static {
        Map<Character, BinaryOperator> tmp = new ConcurrentHashMap<>();
        tmp.put('+', ADD);
        tmp.put('-', SUBTRACT);
        SYMBOL_TO_ENUM = Collections.unmodifiableMap(tmp);

        StringBuilder sb = new StringBuilder();
        SYMBOL_TO_ENUM.keySet().forEach(k -> sb.append(k));
        AVAILABLE_OPS = sb.toString();
    }

    BinaryOperator(BinaryFunction f) {
        this.func = f;
    }

    @FunctionalInterface
    private interface BinaryFunction {
        int getResult(int left, int right);
    }

    public static BinaryOperator getOperator(char op) {
        return SYMBOL_TO_ENUM.get(op);
    }

    public int operate(int left, int right) {
        return func.getResult(left, right);
    }

    public static String getAvailable() {
        return AVAILABLE_OPS;
    }
}