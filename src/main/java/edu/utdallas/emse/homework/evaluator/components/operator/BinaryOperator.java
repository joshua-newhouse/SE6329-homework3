package edu.utdallas.emse.homework.evaluator.components.operator;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BinaryOperator {
    private static final Map<Character, BinaryOperator> symbolToOperator;

    static {
        Map<Character, BinaryOperator> tmp = new ConcurrentHashMap<>();
        tmp.put('+', AdditionOperator.getInstance());
        tmp.put('-', SubtractionOperator.getInstance());

        symbolToOperator = Collections.unmodifiableMap(tmp);
    }

    public static BinaryOperator getOperator() {
        return getOperator((char)0);
    }

    public static BinaryOperator getOperator(char symbol) {
        BinaryOperator retVal = symbolToOperator.get(symbol);

        return retVal == null ? ShiftOperator.getInstance() : retVal;
    }

    public abstract int operate(int left, int right);
}
