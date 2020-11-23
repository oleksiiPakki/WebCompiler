package io.teamdev.javaclasses.impl.runtime;

public class GreaterThanBinaryOperator implements BooleanBinaryOperator {
    private final int priority;


    public GreaterThanBinaryOperator(int priority) {
        this.priority = priority;
    }

    @Override
    public BooleanValueHolder execute(ValueHolder leftOperand, ValueHolder rightOperand){

        boolean greaterThan = DoubleValueReader.readDouble(leftOperand) > DoubleValueReader.readDouble(rightOperand);

        return new BooleanValueHolder(greaterThan);
    }

    @Override
    public int priority() {
        return priority;
    }
}
