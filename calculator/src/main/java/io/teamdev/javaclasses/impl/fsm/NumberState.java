package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.DeadLockException;
import io.teamdev.javaclasses.impl.abstracts.FSMFactory;
import io.teamdev.javaclasses.impl.abstracts.State;
import io.teamdev.javaclasses.impl.runtime.Command;

import java.text.CharacterIterator;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of State.
 * Fsm being on this state when it finds a number, represented by string
 * <p>
 * define the class, representing the result of execution on this state
 */
public class NumberState extends State<List<Command>> {

    private final boolean mayBeFinish;
    private final boolean isLexeme;
    private final FSMFactory factory;

    NumberState(FSMFactory factory) {
        this.mayBeFinish = true;
        this.isLexeme = true;
        this.factory = factory;
    }

    /**
     * @return whether state may be finish or not
     */
    @Override
    public boolean mayBeFinish() {
        return mayBeFinish;
    }

    @Override
    public boolean isLexeme() {
        return isLexeme;
    }

    /**
     * Define a single number in math expression, and push it into shunting yard
     *
     * @param inputSequence  String, contains math expression
     * @param outputSequence Shunting yard with the result of being fsm on this state
     * @return Whether fsm being on this state or not
     */
    @Override
    public boolean accept(CharacterIterator inputSequence, List<Command> outputSequence) throws DeadLockException {


        Optional<List<Command>> possibleCommands = factory.create(FSMFactory.TypeFSM.NUMBER)
                .execute(inputSequence);

        if (possibleCommands.isPresent()) {

            List<Command> commands = possibleCommands.get();

            outputSequence.addAll(commands);

            return true;
        }

        return false;

    }
}

