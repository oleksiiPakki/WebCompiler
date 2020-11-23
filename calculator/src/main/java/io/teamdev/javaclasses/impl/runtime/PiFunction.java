package io.teamdev.javaclasses.impl.runtime;

import java.util.List;
import java.util.Optional;

public class PiFunction implements Function {

    @Override
    public void execute(RuntimeEnvironment environment, List<ValueHolder> arguments) throws WrongCountOfArgumentsException {

        if (!arguments.isEmpty()) {
            throw new WrongCountOfArgumentsException("PI function must contains no elements");
        }

        environment.topStack().pushOperand(new DoubleValueHolder(
                Optional.of(Math.round(Math.PI * 100.0) / 100.0).get()));


}

}
