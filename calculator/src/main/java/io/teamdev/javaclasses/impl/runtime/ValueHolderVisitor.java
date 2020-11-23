package io.teamdev.javaclasses.impl.runtime;

public interface ValueHolderVisitor {

    void visit(DoubleValueHolder holder);

    void visit(BooleanValueHolder holder);

}
