package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.*;
import io.teamdev.javaclasses.impl.runtime.Command;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CalculableFiniteStateMachine extends FiniteStateMachine<List<Command>> {

    public CalculableFiniteStateMachine(FSMFactory factory) {

        State<List<Command>> numberState = new NumberState(factory);
        State<List<Command>> expressionWithBrackets = new ExpressionWithBracketsState(factory);
        State<List<Command>> functionState = new FunctionState(factory);
        State<List<Command>> variableState = new VariableState();

        addStartedStates(
                Arrays.asList(numberState, expressionWithBrackets, variableState, functionState));
    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) throws DeadLockException {
        return calculable(inputSequence);
    }

    public Optional<List<Command>> calculable(CharacterIterator inputSequence) throws DeadLockException {

            List<Command> commands = new ArrayList<>();

            boolean isSuccess = run(inputSequence, commands);

            if (isSuccess) {
                return Optional.of(commands);
            }


        return Optional.empty();

    }
}
