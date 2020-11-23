package io.teamdev.javaclasses.calculatortests;

import io.teamdev.javaclasses.calculator.MathExpressionCalculator;
import io.teamdev.javaclasses.impl.abstracts.IncorrectFormatOfExpressionException;
import io.teamdev.javaclasses.impl.runtime.ProgramExecutionException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test for API MathCalculator.
 */

class MathExpressionCalculatorTest {

    private final MathExpressionCalculator mathCalculator = new MathExpressionCalculator();


    @ParameterizedTest
    @MethodSource("ExpressionWithBrackets")
    void testCorrectEvaluationOfExpressionWithBrackets(String input, String expected)
            throws IncorrectFormatOfExpressionException, ProgramExecutionException {

        assertOutputValue(input, expected, "Expression with brackets is failed");
    }

    private static Stream<Arguments> ExpressionWithBrackets() {
        return Stream.of(
                Arguments.of("2 * (5 + 7 * (4 + 1)) + 20", "100.0"),
                Arguments.of("(2 + 1) * (-2) ", "-6.0"),
                Arguments.of("4 * (0.1 * 100) + 12", "52.0")

        );
    }

    @ParameterizedTest
    @MethodSource("ExpressionWithFunctions")
    void testCorrectEvaluationOfExpressionWithFunctions(String input, String expected)
            throws IncorrectFormatOfExpressionException, ProgramExecutionException {

        assertOutputValue(input, expected, "Expression with functions is failed");
    }

    private static Stream<Arguments> ExpressionWithFunctions() {
        return Stream.of(
                Arguments.of("min(max(-5,-6,-7), 2, 3 ) + max(1,2,3) * pow(2,3)", "19.0"),
                Arguments.of("sum(1, 2, 3)", "6.0")

        );
    }

    @ParameterizedTest
    @MethodSource("BooleanExpression")
    void testCorrectEvaluationOfBooleanExpression(String input, String expected)
            throws IncorrectFormatOfExpressionException, ProgramExecutionException {

        assertOutputValue(input, expected, "Boolean expression is failed");
    }

    private static Stream<Arguments> BooleanExpression() {
        return Stream.of(
                Arguments.of("PI() >= PI()", "true"),
                Arguments.of("max(max(4,10)) < min(2+2,3+3) * 2", "false")

        );
    }

    @ParameterizedTest
    @MethodSource("parsingExpressionNegativeTestCase")
    void testEvaluationOfIncorrectFormatExpression(String input, String expected) {

        assertIncorrectFormatException(input, expected);
    }

    private static Stream<Arguments> parsingExpressionNegativeTestCase() {
        return Stream.of(
                Arguments.of("123error", "Deadlock on 3 position"),
                Arguments.of("3 + 2 */ 1", "Deadlock on 7 position"),
                Arguments.of("*34 - 5", "Deadlock on 0 position"),
                Arguments.of("35 - 7 + 9)", "Deadlock on 10 position")

        );
    }

    @ParameterizedTest
    @MethodSource("executionExpressionNegativeTestCase")
    void testEvaluationOfIncorrectExpression(String input, String expected) {

        assertExecutionException(input, expected);
    }

    private static Stream<Arguments> executionExpressionNegativeTestCase() {
        return Stream.of(
                Arguments.of("PI(1) >= PI()", "PI function must contains no elements"),
                Arguments.of("max() + 3", "Max function must contain at least one argument"),
                Arguments.of("pow(1,2,3)", "Pow function must contains only two arguments")

        );
    }

    private void assertOutputValue(String input, String expected, String message)
            throws IncorrectFormatOfExpressionException, ProgramExecutionException {

        String resultOfExecution = mathCalculator.evaluate(input);

        assertWithMessage(message)
                .that(resultOfExecution)
                .isEqualTo(expected);
    }

    private void assertIncorrectFormatException(String program, String expectedMassage) {

        IncorrectFormatOfExpressionException ex =
                assertThrows(IncorrectFormatOfExpressionException.class, () ->
                        mathCalculator.evaluate(program));
        assertThat(ex).hasMessageThat()
                .contains(expectedMassage);
    }

    private void assertExecutionException(String program, String expectedMassage) {

        ProgramExecutionException ex =
                assertThrows(ProgramExecutionException.class, () ->
                        mathCalculator.evaluate(program));
        assertThat(ex).hasMessageThat()
                .contains(expectedMassage);
    }


}

