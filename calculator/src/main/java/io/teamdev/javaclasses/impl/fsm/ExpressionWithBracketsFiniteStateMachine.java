package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.*;
import io.teamdev.javaclasses.impl.runtime.Command;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ExpressionWithBracketsFiniteStateMachine
        extends FiniteStateMachine<List<Command>> {

    public ExpressionWithBracketsFiniteStateMachine(FSMFactory factory) {

        State<List<Command>> openingBracketForExpressionState = new OpeningBracketForExpressionState(
                false,
                true,
                '(');
        State<List<Command>> expressionState = new BooleanExpressionState(false, factory);
        State<List<Command>> closingBracketState = new ClosingBracketState(')');

        openingBracketForExpressionState.addTransition(expressionState);
        expressionState.addTransition(closingBracketState);

        addStartedStates(Collections.singleton(openingBracketForExpressionState));
    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) throws DeadLockException {
        return expressionWithBrackets(inputSequence);
    }

    public Optional<List<Command>> expressionWithBrackets(CharacterIterator inputSequence) throws DeadLockException {

        List<Command> commands = new ArrayList<>();

        boolean isSuccess = run(inputSequence, commands);

        if (isSuccess) {
            return Optional.of(commands);
        }

        return Optional.empty();
    }
}
