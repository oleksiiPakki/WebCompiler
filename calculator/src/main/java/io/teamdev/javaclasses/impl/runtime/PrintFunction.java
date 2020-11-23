package io.teamdev.javaclasses.impl.runtime;

import java.util.List;

public class PrintFunction implements Function {

    @Override
    public void execute(RuntimeEnvironment environment, List<ValueHolder> arguments) {

        for (ValueHolder argument : arguments){

            environment.output().append(argument.toString()).append(",");
        }

        environment.output().deleteCharAt(environment.output().length() - 1);

    }

}
