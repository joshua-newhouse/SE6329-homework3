package edu.utdallas.emse.homework.evaluator.components.states;

import edu.utdallas.emse.homework.evaluator.components.BinaryOperator;
import edu.utdallas.emse.homework.evaluator.components.EvaluatorContext;
import edu.utdallas.emse.homework.evaluator.components.EvaluatorStateException;

public class EvaluatorSpaceState implements EvaluatorState {
    public void consume(char input, EvaluatorContext context) {
        SpaceHandler handler = (context.getPreviousState() instanceof EvaluatorOperatorState) ?
                new OperatorSpaceHandler() : new OperandSpaceHandler();

        switch (input) {
            case ' ':
                break;
            default:
                handler.handle(input, context);
        }
    }

    private void throwException(char input) {
        throw new EvaluatorStateException(input, this.getClass());
    }

    private interface SpaceHandler {
        void handle(char input, EvaluatorContext context);
    }

    private class OperatorSpaceHandler implements SpaceHandler {
        public void handle(char input, EvaluatorContext context) {
            switch (input) {
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
                    context.setState(new EvaluatorOperandState());
                    break;
                default:
                    throwException(input);
            }
        }
    }

    private class OperandSpaceHandler implements SpaceHandler {
        public void handle(char input, EvaluatorContext context) {
            switch (input) {
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
                    throwException(input);
            }
        }
    }
}