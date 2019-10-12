package edu.utdallas.emse.homework.evaluator.components.operator;

public class AdditionOperator extends BinaryOperator {
    private static final AdditionOperator INSTANCE;

    static {
        INSTANCE = new AdditionOperator();
    }

    private AdditionOperator() {}

    public static AdditionOperator getInstance() {
        return INSTANCE;
    }

    @Override
    public int operate(int left, int right) {
        return left + right;
    }
}
