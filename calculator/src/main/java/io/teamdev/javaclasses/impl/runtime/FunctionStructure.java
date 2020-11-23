package io.teamdev.javaclasses.impl.runtime;

import java.util.ArrayList;
import java.util.List;

public class FunctionStructure {

    private final List<Command> evaluatingArguments = new ArrayList<>();
    private Function currentFunction;

    public void setFunction(Function currentFunction) {
        this.currentFunction = currentFunction;
    }

    public void addEvaluatingArgument(Command argument) {
        evaluatingArguments.add(argument);
    }

    public void execute(RuntimeEnvironment environment) throws
            ProgramExecutionException {
        List<ValueHolder> arguments = new ArrayList<>();

        for (Command command : evaluatingArguments) {
            command.execute(environment);

            ValueHolder possibleResult = environment.closeTopStack()
                    .getResult();


            arguments.add(possibleResult);
        }

        currentFunction.execute(environment, arguments);

    }

}
