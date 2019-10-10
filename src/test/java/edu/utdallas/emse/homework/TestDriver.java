package edu.utdallas.emse.homework;

import edu.utdallas.emse.homework.evaluator.ExpressionEvaluator;

import java.util.ArrayList;
import java.util.List;

public class TestDriver {
    private static final ExpressionEvaluator evaluator = new ExpressionEvaluator();

    public static void main(String[] args) {
        List<String> expressions = new ArrayList<>();
        expressions.add("9 + 10 + 10 - 2\n");
        expressions.add("9+10+10-2\n");
        expressions.add("  9 + 10 + 10 - 2\n");
        expressions.add("09 + 10 + 10 - 2\n");
        expressions.add("+ 10 + 10 - 2\n");
        expressions.add("9 + 10 + 10 - \n");
        expressions.add("9 + 10 + 010 - 2\n");
        expressions.add("9 + 10 + 010 - 2\n");
        expressions.add("9 + 10 + 1 0 - 2\n");
        expressions.add("9 + 10 * 10 - 2\n");
        expressions.add("9 + 10.3 + 10 - 2\n");
        expressions.add("912+ 10 +10 -2 \n");
        expressions.add("9- 10  -2 \n");

        List<String> results = evaluator.evaluate(expressions);

        List<String> expectedResults = new ArrayList<>();
        expectedResults.add("27");
        expectedResults.add("27");
        expectedResults.add("27");
        expectedResults.add("27");
        expectedResults.add("ERROR");
        expectedResults.add("ERROR");
        expectedResults.add("ERROR");
        expectedResults.add("ERROR");
        expectedResults.add("ERROR");
        expectedResults.add("ERROR");
        expectedResults.add("ERROR");
        expectedResults.add("930");
        expectedResults.add("-3");

        for (int i = 0; i < results.size(); i++) {
            String result = results.get(i);
            String expected = expectedResults.get(i);
            assert result.equals(expected);
        }
    }
}
