package edu.utdallas.emse.homework.evaluator.components;

import edu.utdallas.emse.homework.evaluator.components.operator.BinaryOperator;
import edu.utdallas.emse.homework.evaluator.components.states.EvaluatorStartState;
import edu.utdallas.emse.homework.evaluator.components.states.EvaluatorState;

public class EvaluatorContext {
    private int currentTotal = 0;
    private int currentOperand = 0;
    private BinaryOperator currentOperator = BinaryOperator.getOperator((char)0);
    private EvaluatorState currentState = EvaluatorStartState.getInstance();
    private EvaluatorState previousState = null;

    public int getCurrentTotal() {
        return this.currentTotal;
    }

    public void addToOperand(int addend) {
        this.currentOperand = this.currentOperand * 10 + addend;
    }

    public void setCurrentOperator(BinaryOperator operator) {
        this.currentOperator = operator;
    }

    public void operate() {
        currentTotal = currentOperator.operate(currentTotal, currentOperand);
        currentOperand = 0;
    }

    public EvaluatorState getPreviousState() {
        return this.previousState;
    }

    public void setState(EvaluatorState state) {
        this.previousState = this.currentState;
        this.currentState = state;
    }

    public void getNext(char input) {
        if (currentState == null) {
            return;
        }

        currentState.consume(input, this);
    }
}
