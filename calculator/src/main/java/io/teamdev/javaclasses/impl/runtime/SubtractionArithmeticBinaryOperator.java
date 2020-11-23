package io.teamdev.javaclasses.impl.runtime;

/**
 * Operator of subtracting of two operands
 */
public class SubtractionArithmeticBinaryOperator implements ArithmeticBinaryOperator {

    private final int priority;

    public SubtractionArithmeticBinaryOperator(int priority) {
        this.priority = priority;
    }

    @Override
    public DoubleValueHolder execute(ValueHolder leftOperand, ValueHolder rightOperand) {

        double result = DoubleValueReader.readDouble(leftOperand) - DoubleValueReader.readDouble(rightOperand);

        return new DoubleValueHolder(result);
    }

    /**
     * @return priority of execution of subtracting operator (priority is 1)
     */

    @Override
    public int priority() {
        return priority;
    }
}
