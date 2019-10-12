package edu.utdallas.emse.homework.evaluator.components.operator;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class BinaryOperator {
    private static final Map<Character, BinaryOperator> symbolToOperator;

    static {
        Map<Character, BinaryOperator> tmp = new ConcurrentHashMap<>();
        tmp.put('+', new BinaryOperator() {
            @Override
            public int operate(int left, int right) {
                return left + right;
            }
        });

        tmp.put('-', new BinaryOperator() {
            @Override
            public int operate(int left, int right) {
                return left - right;
            }
        });

        /* Shift operator */
        tmp.put((char)0, new BinaryOperator() {
            @Override
            public int operate(int left, int right) {
                return right;
            }
        });

        symbolToOperator = Collections.unmodifiableMap(tmp);
    }

    public static BinaryOperator getOperator() {
        /* Shift operator is the default */
        return getOperator((char)0);
    }

    public static BinaryOperator getOperator(char symbol) {
        BinaryOperator retVal = symbolToOperator.get(symbol);

        return retVal == null ? symbolToOperator.get((char)0) : retVal;
    }

    public abstract int operate(int left, int right);
}
