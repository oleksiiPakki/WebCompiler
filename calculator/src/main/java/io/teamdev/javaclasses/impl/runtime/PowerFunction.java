package io.teamdev.javaclasses.impl.runtime;

import java.util.ArrayDeque;
import java.util.List;

public class PowerFunction implements Function {

    @Override
    public void execute(RuntimeEnvironment environment, List<ValueHolder> arguments)
            throws WrongCountOfArgumentsException {

        final int requiredCountOfArguments = 2;

        if (requiredCountOfArguments != arguments.size()) {
            throw new WrongCountOfArgumentsException(
                    "Pow function must contains only two arguments");
        }

        ArrayDeque<Double> argumentsOfFunction = new ArrayDeque<>();

        for (ValueHolder argument : arguments) {
            argumentsOfFunction.add(DoubleValueReader.readDouble(argument));
        }

        double base = argumentsOfFunction.pop();
        double exponent = argumentsOfFunction.pop();

        environment.topStack()
                   .pushOperand(new DoubleValueHolder(Math.pow(base, exponent)));
    }

}
