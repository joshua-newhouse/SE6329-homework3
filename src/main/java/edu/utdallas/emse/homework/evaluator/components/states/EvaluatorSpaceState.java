package edu.utdallas.emse.homework.evaluator.components.states;

import edu.utdallas.emse.homework.evaluator.components.BinaryOperator;
import edu.utdallas.emse.homework.evaluator.components.EvaluatorContext;
import edu.utdallas.emse.homework.evaluator.components.EvaluatorStateException;

public class EvaluatorSpaceState implements EvaluatorState {
    private static final EvaluatorSpaceState INSTANCE;

    static {
        INSTANCE = new EvaluatorSpaceState();
    }

    public static EvaluatorSpaceState getInstance() {
        return INSTANCE;
    }

    private EvaluatorSpaceState() {
    }

    public void consume(char input, EvaluatorContext context) {
        EvaluatorState transitionHandler = (context.getPreviousState() instanceof EvaluatorOperatorState) ?
                new OperatorSpaceState() : new OperandSpaceState();

        if (input != ' ') {
            transitionHandler.consume(input, context);
        }
    }

    private class OperatorSpaceState implements EvaluatorState {
        private final String EXPECTED_INPUTS = "1-9, space";

        @Override
        public void consume(char input, EvaluatorContext context) {
            if (input > '0' && input <= '9') {
                context.addToOperand(input - '0');
                context.setState(EvaluatorOperandState.getInstance());
            } else {
                throw new EvaluatorStateException(input, this.getClass(), EXPECTED_INPUTS);
            }
        }
    }

    private class OperandSpaceState implements EvaluatorState {
        private final String EXPECTED_INPUTS = "space, newline, " + BinaryOperator.getAvailable();

        @Override
        public void consume(char input, EvaluatorContext context) {
            if (BinaryOperator.getOperator(input) != null) {
                context.operate();
                context.setCurrentOperator(BinaryOperator.getOperator(input));
                context.setState(EvaluatorOperatorState.getInstance());
            } else if (input == '\n') {
                context.operate();
                context.setState(null);
            } else {
                throw new EvaluatorStateException(input, this.getClass(), EXPECTED_INPUTS);
            }
        }
    }
}
