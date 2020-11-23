package io.teamdev.javaclasses.impl.runtime;

public class LessThanBinaryOperator implements BooleanBinaryOperator {
    private final int priority;

    public LessThanBinaryOperator(int priority) {
        this.priority = priority;
    }

    @Override
    public BooleanValueHolder execute(ValueHolder leftOperand, ValueHolder rightOperand){

        boolean lessThan = DoubleValueReader.readDouble(leftOperand) < DoubleValueReader.readDouble(rightOperand);

        return new BooleanValueHolder(lessThan);
    }

    @Override
    public int priority() {
        return priority;
    }
}
