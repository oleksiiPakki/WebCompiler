package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.FiniteStateMachine;
import io.teamdev.javaclasses.impl.abstracts.State;
import io.teamdev.javaclasses.impl.runtime.Command;

import java.text.CharacterIterator;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DefaultFiniteStateMachine extends FiniteStateMachine<SwitchStructure> {
    public DefaultFiniteStateMachine() {

        State<SwitchStructure> defaultWordState = new DefaultWordState<>(false, true);
        State<SwitchStructure> colonState = new TransitState<>(false, true, ':');
        State<SwitchStructure> leftCurlyBracket = new TransitState<>(false, true, '{');
        State<SwitchStructure> listOfExecutingStatementsState = new ListOfDefaultExecutingStatementsState(false, true);
        State<SwitchStructure> rightCurlyBracket = new TransitState<>(true, true, '}');

        defaultWordState.addTransition(colonState);

        colonState.addTransition(leftCurlyBracket);

        leftCurlyBracket.addTransition(listOfExecutingStatementsState);

        listOfExecutingStatementsState.addTransition(rightCurlyBracket);

        addStartedStates(Collections.singletonList(defaultWordState));
    }


    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) {
        return Optional.empty();
    }
}
