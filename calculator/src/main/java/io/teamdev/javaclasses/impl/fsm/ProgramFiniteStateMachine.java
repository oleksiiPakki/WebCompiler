package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.FiniteStateMachine;
import io.teamdev.javaclasses.impl.abstracts.State;
import io.teamdev.javaclasses.impl.runtime.Command;

import java.text.CharacterIterator;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ProgramFiniteStateMachine extends FiniteStateMachine<List<Command>> {

    public ProgramFiniteStateMachine() {
        State<List<Command>> listOfStatementsState = new ListOfStatementsState(true, true);
        State<List<Command>> switchState = new SwitchState(true, true);

        listOfStatementsState.addTransition(switchState);

        switchState.addTransition(listOfStatementsState);

        addStartedStates(Collections.singletonList(listOfStatementsState));
    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) {
        return Optional.empty();
    }

}
