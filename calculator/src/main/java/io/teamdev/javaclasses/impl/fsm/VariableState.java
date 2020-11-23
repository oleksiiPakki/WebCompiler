package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.DeadLockException;
import io.teamdev.javaclasses.impl.abstracts.State;
import io.teamdev.javaclasses.impl.runtime.Command;
import io.teamdev.javaclasses.impl.runtime.ValueHolder;

import java.text.CharacterIterator;
import java.util.List;

public class VariableState extends State<List<Command>> {

    private final boolean mayBeFinish;
    private final boolean isLexeme;

    VariableState() {
        this.mayBeFinish = true;
        this.isLexeme = true;
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

            StringBuilder possibleNameOfVariable = new StringBuilder();

            int positionBeforeParsingNameOfVariable = inputSequence.getIndex();


            boolean isSuccess = new NameFiniteStateMachine().run(inputSequence,
                                                                   possibleNameOfVariable);

            if (isSuccess) {

                if (inputSequence.current() == '(') {

                    inputSequence.setIndex(positionBeforeParsingNameOfVariable);

                    return false;
                }

                outputSequence.add(environment -> {
                    environment.startNewStack();

                    ValueHolder valueOfVariable = environment.value(
                            possibleNameOfVariable.toString());

                    environment.topStack()
                               .pushOperand(valueOfVariable);

                });
            }

            return isSuccess;

    }
}
