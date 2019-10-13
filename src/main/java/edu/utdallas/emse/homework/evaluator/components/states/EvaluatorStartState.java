package edu.utdallas.emse.homework.evaluator.components.states;

import edu.utdallas.emse.homework.evaluator.components.EvaluatorContext;
import edu.utdallas.emse.homework.evaluator.components.EvaluatorStateException;

public class EvaluatorStartState implements EvaluatorState {
    private static final EvaluatorStartState INSTANCE;
    private static final String EXPECTED_INPUTS = "0-9, space";

    static {
        INSTANCE = new EvaluatorStartState();
    }

    public static EvaluatorStartState getInstance() {
        return INSTANCE;
    }

    private EvaluatorStartState() {
    }

    public void consume(char input, EvaluatorContext context) {
        if (input == ' ' || input == '0') {
            /* do nothing */
        } else if (input > '0' && input <= '9') {
            context.addToOperand(input - '0');
            context.setState(EvaluatorOperandState.getInstance());
        } else {
            throw new EvaluatorStateException(input, this.getClass(), EXPECTED_INPUTS);
        }
    }
}
