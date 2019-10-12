package edu.utdallas.emse.homework.evaluator.components.states;

import edu.utdallas.emse.homework.evaluator.components.BinaryOperator;
import edu.utdallas.emse.homework.evaluator.components.EvaluatorContext;
import edu.utdallas.emse.homework.evaluator.components.EvaluatorStateException;

public class EvaluatorOperandState implements EvaluatorState {
    private static final EvaluatorOperandState INSTANCE;
    private static final String EXPECTED_INPUTS = "0-9, space, newline, " + BinaryOperator.getAvailable();

    static {
        INSTANCE = new EvaluatorOperandState();
    }

    public static EvaluatorOperandState getInstance() {
        return INSTANCE;
    }

    private EvaluatorOperandState() {
    }

    public void consume(char input, EvaluatorContext context) {
        if (input >= '0' && input <= '9') {
            context.addToOperand(input - '0');
        } else if (input == ' ') {
            context.setState(EvaluatorSpaceState.getInstance());
        } else if (input == '\n') {
            context.operate();
            context.setState(null);
        } else if (BinaryOperator.getOperator(input) != null) {
            context.operate();
            context.setCurrentOperator(BinaryOperator.getOperator(input));
            context.setState(EvaluatorOperatorState.getInstance());
        } else {
            throw new EvaluatorStateException(input, this.getClass(), EXPECTED_INPUTS);
        }
    }
}
