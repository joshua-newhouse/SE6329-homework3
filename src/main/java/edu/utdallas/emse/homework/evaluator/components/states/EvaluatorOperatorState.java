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
        switch (input) {
            case ' ':
                context.setState(EvaluatorSpaceState.getInstance());
                break;
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                context.addToOperand(input - '0');
                context.setState(EvaluatorOperandState.getInstance());
                break;
            default:
                throw new EvaluatorStateException(input, this.getClass(), EXPECTED_INPUTS);
        }
    }
}
