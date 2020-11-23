package io.teamdev.javaclasses.impl.runtime;

public interface ArithmeticBinaryOperator extends BinaryOperator {

    @Override
    DoubleValueHolder execute(ValueHolder leftOperand, ValueHolder rightOperand ) ;

}
