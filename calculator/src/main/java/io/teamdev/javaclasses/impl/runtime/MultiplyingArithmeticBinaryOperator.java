package io.teamdev.javaclasses.impl.runtime;

/**
 * Operator of multiplying of two operands
 */
public class MultiplyingArithmeticBinaryOperator implements ArithmeticBinaryOperator {

    private final int priority;

    public MultiplyingArithmeticBinaryOperator(int priority) {
        this.priority = priority;
    }


    @Override
    public DoubleValueHolder execute(ValueHolder leftOperand, ValueHolder rightOperand) {

        double result = DoubleValueReader.readDouble(leftOperand) * DoubleValueReader.readDouble(rightOperand);

        return new DoubleValueHolder(result);
    }

    /**
     * @return priority of execution of adding operator (priority is 1)
     */
    @Override
    public int priority() {
        return priority;
    }

}
