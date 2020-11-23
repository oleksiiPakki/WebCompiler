package io.teamdev.javaclasses.impl.abstracts;

public interface FSMFactory {

    enum TypeFSM {
        NUMBER,
        EXPRESSION,
        EXPRESSION_WITH_BRACKETS,
        FUNCTION,
        CALCULABLE,
        BOOLEAN_EXPRESSION,
        INITIALIZATION,
        STATEMENT
    }
    Compiler create(TypeFSM typeFSM);
}
