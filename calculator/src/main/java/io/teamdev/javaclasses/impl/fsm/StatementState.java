package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.DeadLockException;
import io.teamdev.javaclasses.impl.runtime.Command;
import io.teamdev.javaclasses.impl.abstracts.FSMFactory;
import io.teamdev.javaclasses.impl.abstracts.State;

import java.text.CharacterIterator;
import java.util.List;
import java.util.Optional;

public class StatementState extends State<List<Command>> {

    public final boolean mayBeFinish;
    private final boolean isLexeme;

    public StatementState(boolean mayBeFinish, boolean isLexeme) {
        this.mayBeFinish = mayBeFinish;
        this.isLexeme = isLexeme;
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
    public boolean accept(CharacterIterator inputSequence, List<Command> outputSequence) throws DeadLockException {
            FSMFactory factory = new FSMFactoryImpl();

            Optional<List<Command>> commands = factory.create(FSMFactory.TypeFSM.STATEMENT).execute(inputSequence);

            boolean isSuccess = commands.isPresent();

            if (isSuccess) {
                outputSequence.add(environment -> {

                    for (Command command : commands.get()) {
                        command.execute(environment);
                    }
                });
            }

            return isSuccess;


    }
}
