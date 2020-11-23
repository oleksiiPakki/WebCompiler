package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.FiniteStateMachine;
import io.teamdev.javaclasses.impl.abstracts.State;
import io.teamdev.javaclasses.impl.runtime.Command;

import java.text.CharacterIterator;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SwitchFiniteStateMachine extends FiniteStateMachine<SwitchStructure> {

    public SwitchFiniteStateMachine() {

        State<SwitchStructure> switchWordState = new SwitchWordState<>(false, true);
        State<SwitchStructure> openingBracketState = new TransitState<>(false, true, '(');
        State<SwitchStructure> variableOfSwitchState = new SwitchVariableState(false, true);
        State<SwitchStructure> verticalLineState = new TransitState<>(false, true, '|');
        State<SwitchStructure> closingBracketState = new TransitState<>(false, true, ')');
        State<SwitchStructure> leftCurlyBracketState = new TransitState<>(false, true, '{');
        State<SwitchStructure> caseState = new CaseState(false, true);
        State<SwitchStructure> defaultState = new DefaultState(false, true);
        State<SwitchStructure> rightCurlyBracket = new TransitState<>(true, true, '}');

        switchWordState.addTransition(openingBracketState);

        openingBracketState.addTransition(variableOfSwitchState);

        variableOfSwitchState.addTransition(verticalLineState);
        variableOfSwitchState.addTransition(closingBracketState);

        verticalLineState.addTransition(variableOfSwitchState);

        closingBracketState.addTransition(leftCurlyBracketState);

        leftCurlyBracketState.addTransition(caseState);
        leftCurlyBracketState.addTransition(defaultState);

        caseState.addTransition(caseState);
        caseState.addTransition(defaultState);
        caseState.addTransition(rightCurlyBracket);

        defaultState.addTransition(rightCurlyBracket);

        addStartedStates(Collections.singletonList(switchWordState));
    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) {
        return Optional.empty();
    }
}
