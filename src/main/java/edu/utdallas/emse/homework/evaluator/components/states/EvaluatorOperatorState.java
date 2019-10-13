package edu.utdallas.emse.homework.evaluator.components.states;

import edu.utdallas.emse.homework.evaluator.components.EvaluatorContext;
import edu.utdallas.emse.homework.evaluator.components.EvaluatorStateException;

public class EvaluatorOperatorState implements EvaluatorState {
    private static final EvaluatorOperatorState INSTANCE;
    private static final String EXPECTED_INPUTS = "1-9, space";

    static {
        INSTANCE = new EvaluatorOperatorState();
    }

    public static EvaluatorOperatorState getInstance() {
        return INSTANCE;
    }

    private EvaluatorOperatorState() {
    }

    public void consume(char input, EvaluatorContext context) {
        if (input == ' ') {
            context.setState(EvaluatorSpaceState.getInstance());
        } else if (input > '0' && input <= '9') {
            context.addToOperand(input - '0');
            context.setState(EvaluatorOperandState.getInstance());
        } else {
            throw new EvaluatorStateException(input, this.getClass(), EXPECTED_INPUTS);
        }
    }
}
