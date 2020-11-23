package io.teamdev.javaclasses.impl.runtime;

/**
 * Operator of division of two operands
 */
public class DivisionArithmeticBinaryOperator implements ArithmeticBinaryOperator {

    private final int priority;

    public DivisionArithmeticBinaryOperator(int priority) {
        this.priority = priority;
    }


    @Override
    public DoubleValueHolder execute(ValueHolder leftOperand, ValueHolder rightOperand) {

        double result = DoubleValueReader.readDouble(leftOperand) / DoubleValueReader.readDouble(rightOperand);

        return new DoubleValueHolder(result);
    }

    /**
     * @return priority of execution of dividing operator (priority is 2)
     */
    @Override
    public int priority() {
        return priority;
    }
}
