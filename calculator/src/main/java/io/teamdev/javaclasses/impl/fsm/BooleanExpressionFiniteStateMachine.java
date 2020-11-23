package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.*;
import io.teamdev.javaclasses.impl.runtime.Command;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BooleanExpressionFiniteStateMachine extends FiniteStateMachine<List<Command>> {

    public BooleanExpressionFiniteStateMachine(FSMFactory factory) {

        State<List<Command>> expressionState = new ExpressionState(factory);
        State<List<Command>> booleanOperator = new BooleanBinaryOperatorState();

        expressionState.addTransition(booleanOperator);

        booleanOperator.addTransition(expressionState);

        addStartedStates(Collections.singletonList(expressionState));
    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) throws DeadLockException {
        return booleanExpression(inputSequence);
    }

    public Optional<List<Command>> booleanExpression(CharacterIterator inputSequence) throws DeadLockException {
            List<Command> commands = new ArrayList<>();

            boolean isSuccess = run(inputSequence, commands);

            if (isSuccess) {
                return Optional.of(commands);
            }


        return Optional.empty();

    }
}

