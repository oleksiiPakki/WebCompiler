package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.DeadLockException;
import io.teamdev.javaclasses.impl.abstracts.State;

import java.text.CharacterIterator;

public class TransitState<T> extends State<T> {

    private final boolean mayBeFinish;
    private final boolean isLexeme;
    private final Character requiredCharacter;

    public TransitState(boolean mayBeFinish, boolean isLexeme, Character requiredCharacter) {
        this.mayBeFinish = mayBeFinish;
        this.isLexeme = isLexeme;
        this.requiredCharacter = requiredCharacter;
    }

    /**
     * @return whether comma may be finish state or not
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
     * Going to the next character in an input sequence if current character is ','
     *
     * @param inputSequence  String, contains math expression
     * @param outputSequence The result after being on this state
     * @return true if current character is ',', or false if it is not
     */

    @Override
    public boolean accept(CharacterIterator inputSequence, T outputSequence) throws
            DeadLockException {

        if (requiredCharacter.equals(inputSequence.current())) {
            inputSequence.next();

            return true;
        }

        return false;
    }
}
