package io.teamdev.javaclasses.impl.runtime;

public class NotEqualsToBinaryOperator implements BooleanBinaryOperator {

    private final int priority;

    public NotEqualsToBinaryOperator(int priority) {
        this.priority = priority;
    }

    @Override
    public BooleanValueHolder execute(ValueHolder leftOperand, ValueHolder rightOperand){

        boolean notEqualsTo = !DoubleValueReader.readDouble(leftOperand).equals(DoubleValueReader.readDouble(rightOperand));

        return new BooleanValueHolder(notEqualsTo);
    }

    @Override
    public int priority() {

        return priority;
    }
}
