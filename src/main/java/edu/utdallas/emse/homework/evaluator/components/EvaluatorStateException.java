package edu.utdallas.emse.homework.evaluator.components;

public class EvaluatorStateException extends IllegalStateException {
    public EvaluatorStateException(char input, Class<?> state, String expectedInputs) {
        super(
                String.format("Unexpected input %c%c in %s Expected one of: [%s]",
                        input == '\n' ? '\\' : 0,
                        input == '\n' ? 'n' : input,
                        state.getSimpleName(),
                        expectedInputs)
        );
    }
}
