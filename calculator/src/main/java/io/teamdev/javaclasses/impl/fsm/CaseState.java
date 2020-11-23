package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.DeadLockException;
import io.teamdev.javaclasses.impl.abstracts.State;

import java.text.CharacterIterator;

public class CaseState extends State<SwitchStructure> {
    private final boolean mayBeFinish;
    private final boolean isLexeme;

    public CaseState(boolean mayBeFinish, boolean isLexeme) {
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
    public boolean accept(CharacterIterator inputSequence, SwitchStructure outputSequence) throws DeadLockException {

        return new CaseFiniteStateMachine().run(inputSequence, outputSequence);

    }
}
