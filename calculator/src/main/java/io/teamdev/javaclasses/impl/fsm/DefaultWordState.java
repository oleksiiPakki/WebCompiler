package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.DeadLockException;
import io.teamdev.javaclasses.impl.abstracts.State;

import java.text.CharacterIterator;

public class DefaultWordState<T> extends State<T> {

    private final boolean mayBeFinish;
    private final boolean isLexeme;

    public DefaultWordState(boolean mayBeFinish, boolean isLexeme) {
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
    public boolean accept(CharacterIterator inputSequence, T outputSequence) throws DeadLockException {

        return new DefaultWordFiniteStateMachine().run(inputSequence, new StringBuilder());
    }
}
