package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.DeadLockException;
import io.teamdev.javaclasses.impl.abstracts.FSMFactory;
import io.teamdev.javaclasses.impl.abstracts.State;
import io.teamdev.javaclasses.impl.runtime.Command;
import io.teamdev.javaclasses.impl.runtime.ValueHolder;

import java.text.CharacterIterator;
import java.util.List;
import java.util.Optional;

public class ExpressionWithBracketsState extends State<List<Command>> {

    private final boolean mayBeFinish;
    private final boolean isLexeme;
    private final FSMFactory factory;

    ExpressionWithBracketsState(FSMFactory factory) {
        this.mayBeFinish = true;
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
    public boolean accept(CharacterIterator inputSequence, List<Command> outputSequence) throws DeadLockException {

        Optional<List<Command>> possibleCommands = factory.create(
                FSMFactory.TypeFSM.EXPRESSION_WITH_BRACKETS)
                .execute(inputSequence);

        boolean isSuccess = possibleCommands.isPresent();

        if (isSuccess) {
            outputSequence.add((environment) -> {

                for (Command command : possibleCommands.get()) {
                    command.execute(environment);
                }

                ValueHolder resultHolder = environment.topStack().getResult();


                environment.topStack().pushOperand(resultHolder);
            });
        }

        return isSuccess;
    }
}
