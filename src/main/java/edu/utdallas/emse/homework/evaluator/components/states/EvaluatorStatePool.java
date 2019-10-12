package edu.utdallas.emse.homework.evaluator.components.states;

public class EvaluatorStatePool {
    private static EvaluatorStartState startState;
    private static EvaluatorOperandState operandState;
    private static EvaluatorOperatorState operatorState;
    private static EvaluatorSpaceState spaceState;

    public static EvaluatorState getStartState() {
        return startState == null ? startState = new EvaluatorStartState() : startState;
    }

    public static EvaluatorState getOperandState() {
        return operandState == null ? operandState = new EvaluatorOperandState() : operandState;
    }

    public static EvaluatorState getSpaceState() {
        return spaceState == null ? spaceState = new EvaluatorSpaceState() : spaceState;
    }

    public static EvaluatorState getOperatorState() {
        return operatorState == null ? operatorState = new EvaluatorOperatorState() : operatorState;
    }
}
