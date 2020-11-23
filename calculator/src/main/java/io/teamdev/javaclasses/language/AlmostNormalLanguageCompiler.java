package io.teamdev.javaclasses.language;

import io.teamdev.javaclasses.impl.abstracts.DeadLockException;
import io.teamdev.javaclasses.impl.fsm.ProgramFiniteStateMachine;
import io.teamdev.javaclasses.impl.runtime.Command;
import io.teamdev.javaclasses.impl.runtime.ProgramExecutionException;
import io.teamdev.javaclasses.impl.runtime.RuntimeEnvironment;

import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

public class AlmostNormalLanguageCompiler {


    public void evaluate(String mathExpression, RuntimeEnvironment environment) throws ProgramExecutionException, DeadLockException {

        List<Command> commands = new ArrayList<>();

            boolean isSuccess = new ProgramFiniteStateMachine().run(new StringCharacterIterator(mathExpression), commands);

            if (isSuccess) {
                environment.startNewStack();

                for (Command command : commands) {
                    command.execute(environment);
                }

            }



    }

    public static void main(String[] args) {
        System.out.println("Hello from language");
    }
}
