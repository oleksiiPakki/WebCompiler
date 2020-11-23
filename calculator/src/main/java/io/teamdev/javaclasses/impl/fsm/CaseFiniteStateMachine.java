package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.FiniteStateMachine;
import io.teamdev.javaclasses.impl.abstracts.State;
import io.teamdev.javaclasses.impl.runtime.Command;

import java.text.CharacterIterator;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CaseFiniteStateMachine extends FiniteStateMachine<SwitchStructure> {

    public CaseFiniteStateMachine(){

        State<SwitchStructure> caseWordState = new CaseWordState<>(false,true);
        State<SwitchStructure> checkedNumberState = new CheckedNumberState(false, true);
        State<SwitchStructure> colonState = new TransitState<>(false, true, ':');
        State<SwitchStructure> leftCurlyBracketState = new TransitState<>(false, true, '{');
        State<SwitchStructure> ListOfCaseStatementsState = new ListOfCaseExecutingStatementsState(false, true);
        State<SwitchStructure> rightCurlyBracketState = new TransitState<>(true, true, '}');

        caseWordState.addTransition(checkedNumberState);

        checkedNumberState.addTransition(colonState);

        colonState.addTransition(leftCurlyBracketState);

        leftCurlyBracketState.addTransition(ListOfCaseStatementsState);

        ListOfCaseStatementsState.addTransition(rightCurlyBracketState);

        addStartedStates(Collections.singletonList(caseWordState));

    }


    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence)  {
        return Optional.empty();
    }
}
