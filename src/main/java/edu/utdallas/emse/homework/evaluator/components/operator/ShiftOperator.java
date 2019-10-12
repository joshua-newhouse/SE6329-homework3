package edu.utdallas.emse.homework.evaluator.components.operator;

public class ShiftOperator extends BinaryOperator {
    private static final ShiftOperator INSTANCE;

    static {
        INSTANCE = new ShiftOperator();
    }

    private ShiftOperator() {}

    public static ShiftOperator getInstance() {
        return INSTANCE;
    }

    @Override
    public int operate(int left, int right) {
        return right;
    }
}
