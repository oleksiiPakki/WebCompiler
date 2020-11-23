package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.DeadLockException;
import io.teamdev.javaclasses.impl.abstracts.State;
import io.teamdev.javaclasses.impl.runtime.Command;

import java.text.CharacterIterator;
import java.util.List;

public class SwitchState extends State<List<Command>> {
    private final boolean mayBeFinish;
    private final boolean isLexeme;

    public SwitchState(boolean mayBeFinish, boolean isLexeme) {
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

        SwitchStructure switchStructure = new SwitchStructure();

        boolean isSuccess = new SwitchFiniteStateMachine().run(inputSequence, switchStructure);

        if (isSuccess){
            outputSequence.add(switchStructure::execute);
        }

        return isSuccess;

    }
}
