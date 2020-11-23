package io.teamdev.javaclasses.impl.runtime;

public interface ValueHolder {

    void accept(ValueHolderVisitor visitor);
}
