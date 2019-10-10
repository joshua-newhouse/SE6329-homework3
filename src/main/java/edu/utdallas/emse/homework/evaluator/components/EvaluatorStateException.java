package edu.utdallas.emse.homework.evaluator.components;

public class EvaluatorStateException extends IllegalStateException {
    public EvaluatorStateException(char input, Class<?> state) {
        super(
                String.format("Unexpected input %c%c in %s",
                        input == '\n' ? '\\' : 0,
                        input == '\n' ? 'n' : input,
                        state.getSimpleName())
        );
    }
}
