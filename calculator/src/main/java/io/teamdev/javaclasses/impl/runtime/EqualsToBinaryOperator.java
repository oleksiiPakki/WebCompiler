package io.teamdev.javaclasses.impl.runtime;

public class EqualsToBinaryOperator implements BooleanBinaryOperator {
    private final int priority;

    public EqualsToBinaryOperator(int priority) {
        this.priority = priority;
    }

    @Override
    public BooleanValueHolder execute(ValueHolder leftOperand, ValueHolder rightOperand){

        boolean equalsTo = DoubleValueReader.readDouble(leftOperand).equals(DoubleValueReader.readDouble(rightOperand));

        return new BooleanValueHolder(equalsTo);
    }

    @Override
    public int priority() {
        return priority;
    }

}
