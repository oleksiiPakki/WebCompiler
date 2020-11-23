package io.teamdev.javaclasses.impl.runtime;

public class GreaterThanOrEqualsToBinaryOperator implements BooleanBinaryOperator {
    private final int priority;

    public GreaterThanOrEqualsToBinaryOperator(int priority) {
        this.priority = priority;
    }

    @Override
    public BooleanValueHolder execute(ValueHolder leftOperand, ValueHolder rightOperand){

        boolean greaterThanOrEqualsTo = DoubleValueReader.readDouble(leftOperand) >= DoubleValueReader.readDouble(rightOperand);

        return new BooleanValueHolder(greaterThanOrEqualsTo);
    }

    @Override
    public int priority() {
        return priority;
    }

}
