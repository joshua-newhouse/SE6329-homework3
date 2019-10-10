package edu.utdallas.emse.homework.evaluator.components.states;

import edu.utdallas.emse.homework.evaluator.components.EvaluatorContext;

public interface EvaluatorState {
    void consume(char input, EvaluatorContext context);
}
