package io.teamdev.javaclasses.impl.runtime;

public interface BinaryOperator {
    int priority();

    ValueHolder execute(ValueHolder leftOperand, ValueHolder rightOperand);


}
