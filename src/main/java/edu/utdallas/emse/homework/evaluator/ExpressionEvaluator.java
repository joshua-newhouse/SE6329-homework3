package edu.utdallas.emse.homework.evaluator;

import edu.utdallas.emse.homework.evaluator.components.EvaluatorContext;
import edu.utdallas.emse.homework.evaluator.components.EvaluatorStateException;

import java.util.ArrayList;
import java.util.List;

public class ExpressionEvaluator {
    public List<String> evaluate(List<String> expressions) {
        List<String> results = new ArrayList<>();
        expressions.forEach(expression -> results.add(evaluate(expression)));
        return results;
    }

    private String evaluate(String expression) {
        EvaluatorContext context = new EvaluatorContext();

        for (char i : expression.toCharArray()) {
            try {
                context.getNext(i);
            } catch (EvaluatorStateException e) {
                System.out.println(expression.trim() + " = ERROR");
                System.out.println(e.toString());
                return "ERROR";
            }
        }

        System.out.println(expression.trim() + " = " + context.getCurrentTotal());
        return Integer.toString(context.getCurrentTotal());
    }
}
