package io.teamdev.javaclasses.impl.runtime;

public class DoubleValueHolder implements ValueHolder {
    private final double value;

    public DoubleValueHolder(double value) {
        this.value = value;
    }

    public Double value() {
        return value;
    }

    @Override
    public void accept(ValueHolderVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
