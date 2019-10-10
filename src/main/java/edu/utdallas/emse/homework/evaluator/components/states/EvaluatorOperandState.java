package edu.utdallas.emse.homework.evaluator.components.states;

import edu.utdallas.emse.homework.evaluator.components.BinaryOperator;
import edu.utdallas.emse.homework.evaluator.components.EvaluatorContext;
import edu.utdallas.emse.homework.evaluator.components.EvaluatorStateException;

public class EvaluatorOperandState implements EvaluatorState {
    public void consume(char input, EvaluatorContext context) {
        switch (input) {
            case '0':
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
                break;
            case ' ':
                context.setState(new EvaluatorSpaceState());
                break;
            case '+':
            case '-':
                context.operate();
                context.setCurrentOperator(BinaryOperator.getOperator(input));
                context.setState(new EvaluatorOperatorState());
                break;
            case '\n':
                context.operate();
                context.setState(null);
                break;
            default:
                throw new EvaluatorStateException(input, this.getClass());
        }
    }
}