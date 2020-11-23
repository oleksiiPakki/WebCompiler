package io.teamdev.javaclasses.impl.runtime;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MinFunction implements Function {

    @Override
    public void execute(RuntimeEnvironment environment, List<ValueHolder> arguments) throws
                                                                                     WrongCountOfArgumentsException {

        Collection<Double> argumentsOfFunction = new ArrayList<>();

        if (arguments.isEmpty()) {
            throw new WrongCountOfArgumentsException(
                    "Min function must contain at least one argument");
        }

        for (ValueHolder argument : arguments) {
            argumentsOfFunction.add(DoubleValueReader.readDouble(argument));
        }

        environment.topStack()
                   .pushOperand(new DoubleValueHolder(Collections.min(argumentsOfFunction)));
    }

}
