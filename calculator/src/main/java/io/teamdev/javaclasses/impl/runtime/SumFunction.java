package io.teamdev.javaclasses.impl.runtime;

import java.util.List;

public class SumFunction implements Function {

    @Override
    public void execute(RuntimeEnvironment environment, List<ValueHolder> arguments) {
        double sum = 0;

        for (ValueHolder argument : arguments){

            sum += DoubleValueReader.readDouble(argument);
        }

        environment.topStack().pushOperand(new DoubleValueHolder(sum));
    }
}
