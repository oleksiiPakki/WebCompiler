package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.DeadLockException;
import io.teamdev.javaclasses.impl.runtime.Command;
import io.teamdev.javaclasses.impl.abstracts.State;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.List;

public class ListOfCaseExecutingStatementsState extends State<SwitchStructure> {
    private final boolean mayBeFinish;
    private final boolean isLexeme;

    public ListOfCaseExecutingStatementsState(boolean mayBeFinish, boolean isLexeme) {
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

        List<Command> commands = new ArrayList<>();

        boolean isSuccess = new ListOfStatementsFiniteStateMachine().run(inputSequence, commands);

        if (isSuccess){
            outputSequence.addCommandToExecute(environment -> {

                if (outputSequence.whetherExecutingStatements() && !outputSequence.isRequiredNumberHasBeenFounded()) {
                    environment.startNewStack();

                    for (Command command : commands) {
                        command.execute(environment);
                    }

                    outputSequence.requiredNumberHasBeenFounded();
                }
            });
        }

        return isSuccess;
    }
}
