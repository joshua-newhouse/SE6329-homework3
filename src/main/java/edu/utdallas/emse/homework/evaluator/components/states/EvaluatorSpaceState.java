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
        SpaceHandler handler = (context.getPreviousState() instanceof EvaluatorOperatorState) ?
                new OperatorSpaceHandler() : new OperandSpaceHandler();

        if (input != ' ') {
            handler.handle(input, context);
        }
    }

    private interface SpaceHandler {
        void handle(char input, EvaluatorContext context);
    }

    private class OperatorSpaceHandler implements SpaceHandler {
        private static final String EXPECTED_INPUTS = "1-9, space";

        public void handle(char input, EvaluatorContext context) {
            if (input > '0' && input <= '9') {
                context.addToOperand(input - '0');
                context.setState(EvaluatorOperandState.getInstance());
            } else {
                throw new EvaluatorStateException(input, this.getClass(), EXPECTED_INPUTS);
            }
        }
    }

    private class OperandSpaceHandler implements SpaceHandler {
        private static final String EXPECTED_INPUTS = "space, +,-, newline";

        public void handle(char input, EvaluatorContext context) {
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
