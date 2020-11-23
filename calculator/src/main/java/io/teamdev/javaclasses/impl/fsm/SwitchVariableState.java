package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.DeadLockException;
import io.teamdev.javaclasses.impl.abstracts.State;
import io.teamdev.javaclasses.impl.runtime.DoubleValueReader;
import io.teamdev.javaclasses.impl.runtime.ValueHolder;

import java.text.CharacterIterator;

public class SwitchVariableState extends State<SwitchStructure> {
    private final boolean mayBeFinish;
    private final boolean isLexeme;

    public SwitchVariableState(boolean mayBeFinish, boolean isLexeme) {
        this.mayBeFinish = mayBeFinish;
        this.isLexeme = isLexeme;
    }

    @Override
    public boolean isLexeme() {
        return isLexeme;
    }

    @Override
    public boolean mayBeFinish() {
        return mayBeFinish;
    }

    @Override
    public boolean accept(CharacterIterator inputSequence, SwitchStructure outputSequence) throws DeadLockException {
        StringBuilder variableName = new StringBuilder();

        boolean isSuccess = new NameFiniteStateMachine().run(inputSequence, variableName);

        if (isSuccess) {
            outputSequence.addCommandToExecute(environment -> {

                ValueHolder valueHolder = environment.value(variableName.toString());

                outputSequence.addSearchValue(DoubleValueReader.readDouble(valueHolder));
            });
        }

        return isSuccess;
    }
}
