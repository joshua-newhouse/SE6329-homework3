package edu.utdallas.emse.homework.evaluator.components.operator;

public class SubtractionOperator extends BinaryOperator {
    private static final SubtractionOperator INSTANCE;

    static {
        INSTANCE = new SubtractionOperator();
    }

    private SubtractionOperator() {}

    public static SubtractionOperator getInstance() {
        return INSTANCE;
    }

    @Override
    public int operate(int left, int right) {
        return left - right;
    }
}
