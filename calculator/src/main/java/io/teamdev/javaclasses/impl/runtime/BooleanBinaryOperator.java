package io.teamdev.javaclasses.impl.runtime;

public interface BooleanBinaryOperator extends BinaryOperator {

    @Override
    BooleanValueHolder execute(ValueHolder leftOperand, ValueHolder rightOperand);

}
