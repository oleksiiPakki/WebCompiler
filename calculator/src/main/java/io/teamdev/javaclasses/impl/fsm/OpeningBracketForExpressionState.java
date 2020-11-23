package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.State;
import io.teamdev.javaclasses.impl.runtime.Command;
import io.teamdev.javaclasses.impl.runtime.RuntimeEnvironment;

import java.text.CharacterIterator;
import java.util.List;

public class OpeningBracketForExpressionState extends State<List<Command>> {
    private final boolean mayBeFinish;
    private final boolean isLexeme;
    private final Character requiredCharacter;

    public OpeningBracketForExpressionState(boolean mayBeFinish, boolean isLexeme, Character requiredCharacter) {
        this.mayBeFinish = mayBeFinish;
        this.isLexeme = isLexeme;
        this.requiredCharacter = requiredCharacter;
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
    public boolean accept(CharacterIterator inputSequence, List<Command> outputSequence) {
        Character currentCharacter = inputSequence.current();

        if (requiredCharacter.equals(currentCharacter)) {
            outputSequence.add(RuntimeEnvironment::startNewStack);

            inputSequence.next();

            return true;
        }

        return false;
    }
}
