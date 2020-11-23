package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.DeadLockException;
import io.teamdev.javaclasses.impl.abstracts.State;
import io.teamdev.javaclasses.impl.runtime.Command;

import java.text.CharacterIterator;
import java.util.List;

public class NameForVariableState extends State<List<Command>> {
    private final boolean mayBeFinish;
    private final boolean isLexeme;

    public NameForVariableState(boolean mayBeFinish, boolean isLexeme) {
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
        char currentCharacter = inputSequence.current();

        if (Character.isLetter(currentCharacter)) {

            StringBuilder possibleNameOfVariable = new StringBuilder();

            int positionBeforeParsingNameOfVariable = inputSequence.getIndex();

            boolean isSuccess = new NameFiniteStateMachine().run(inputSequence, possibleNameOfVariable);

            if (isSuccess) {

                if (inputSequence.current() == '(') {

                    inputSequence.setIndex(positionBeforeParsingNameOfVariable);

                    return false;
                }

                outputSequence.add(
                        environment -> environment.keepVariable(possibleNameOfVariable.toString()));
            }

            return isSuccess;

        }

        return false;
    }
}
