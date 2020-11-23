package io.teamdev.javaclasses.impl.runtime;

public class DoubleValueReader implements ValueHolderVisitor {
    private Double value;

    public static Double readDouble(ValueHolder holder) {
        DoubleValueReader valueReader = new DoubleValueReader();

        holder.accept(valueReader);

        return valueReader.value();
    }

    @Override
    public void visit(DoubleValueHolder holder) {
        value = holder.value();
    }

    @Override
    public void visit(BooleanValueHolder holder) {

    }

    public double value() {

        if (value == null) {
            throw new IllegalStateException("Boolean value expected");
        }

        return value;
    }
}
