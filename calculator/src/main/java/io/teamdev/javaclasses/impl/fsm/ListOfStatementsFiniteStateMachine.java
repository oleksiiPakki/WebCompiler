package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.FiniteStateMachine;
import io.teamdev.javaclasses.impl.abstracts.State;
import io.teamdev.javaclasses.impl.runtime.Command;

import java.text.CharacterIterator;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ListOfStatementsFiniteStateMachine extends FiniteStateMachine<List<Command>> {

    public ListOfStatementsFiniteStateMachine() {

        State<List<Command>> statementState = new StatementState(false, true);
        State<List<Command>> semicolon = new TransitState<>(true, true, ';');

        statementState.addTransition(semicolon);

        semicolon.addTransition(statementState);

        addStartedStates(Collections.singletonList(statementState));

    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) {
        return Optional.empty();
    }
}
