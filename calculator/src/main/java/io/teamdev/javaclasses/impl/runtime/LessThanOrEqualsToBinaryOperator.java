package io.teamdev.javaclasses.impl.runtime;

public class LessThanOrEqualsToBinaryOperator implements BooleanBinaryOperator {
    private final int priority;

    public LessThanOrEqualsToBinaryOperator(int priority) {
        this.priority = priority;
    }

    @Override
    public BooleanValueHolder execute(ValueHolder leftOperand, ValueHolder rightOperand){

        boolean lessThanOrEqualsTo = DoubleValueReader.readDouble(leftOperand) <= DoubleValueReader.readDouble(rightOperand);

        return new BooleanValueHolder(lessThanOrEqualsTo);
    }

    @Override
    public int priority() {
        return priority;
    }
}
