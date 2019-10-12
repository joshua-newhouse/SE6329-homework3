package edu.utdallas.emse.homework.evaluator.components.states;

import edu.utdallas.emse.homework.evaluator.components.EvaluatorContext;
import edu.utdallas.emse.homework.evaluator.components.EvaluatorStateException;

public class EvaluatorStartState implements EvaluatorState {
    public void consume(char input, EvaluatorContext context) {
        switch (input) {
            case ' ':
            case '0':
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
                context.setState(EvaluatorStatePool.getOperandState());
                break;
            default:
                throw new EvaluatorStateException(input, this.getClass());
        }
    }
}
