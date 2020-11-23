package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.DeadLockException;
import io.teamdev.javaclasses.impl.runtime.Command;
import io.teamdev.javaclasses.impl.abstracts.FSMFactory;
import io.teamdev.javaclasses.impl.abstracts.State;
import io.teamdev.javaclasses.impl.runtime.FunctionStructure;

import java.text.CharacterIterator;
import java.util.List;
import java.util.Optional;

public class ArgumentState extends State<FunctionStructure> {

    private final boolean mayBeFinish;
    private final boolean isLexeme;
    private final FSMFactory factory;

    ArgumentState(FSMFactory factory) {
        this.mayBeFinish = false;
        this.isLexeme = true;
        this.factory = factory;
    }

    @Override
    public boolean mayBeFinish() {
        return mayBeFinish;
    }

    @Override
    public boolean isLexeme() {
        return isLexeme;
    }

    @Override
    public boolean accept(CharacterIterator inputSequence, FunctionStructure outputSequence) throws DeadLockException {

        Optional<List<Command>> possibleCommands =
                factory.create(FSMFactory.TypeFSM.BOOLEAN_EXPRESSION).execute(inputSequence);

        boolean isSuccess = possibleCommands.isPresent();

        if (isSuccess) {
            outputSequence.addEvaluatingArgument(environment -> {

                environment.startNewStack();

                for (Command command : possibleCommands.get()) {
                    command.execute(environment);
                }
            });

        }

        return isSuccess;

    }
}
