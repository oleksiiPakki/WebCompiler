package io.teamdev.javaclasses.impl.runtime;

public class BooleanValueHolder implements ValueHolder  {
    private final boolean value;

    public BooleanValueHolder(boolean value) {
        this.value = value;
    }


    @Override
    public void accept(ValueHolderVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return Boolean.toString(value);
    }

}
