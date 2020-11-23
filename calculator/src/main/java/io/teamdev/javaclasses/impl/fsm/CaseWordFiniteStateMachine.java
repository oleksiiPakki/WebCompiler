package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.FiniteStateMachine;
import io.teamdev.javaclasses.impl.abstracts.State;
import io.teamdev.javaclasses.impl.runtime.Command;

import java.text.CharacterIterator;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CaseWordFiniteStateMachine extends FiniteStateMachine<StringBuilder> {

    public CaseWordFiniteStateMachine() {
        State<StringBuilder> cRequiredCharacterState = new TransitState<>(false, false, 'c');
        State<StringBuilder> aRequiredCharacterState = new TransitState<>(false, false, 'a');
        State<StringBuilder> sRequiredCharacterState = new TransitState<>(false, false, 's');
        State<StringBuilder> eRequiredCharacterState = new TransitState<>(true, false, 'e');

        cRequiredCharacterState.addTransition(aRequiredCharacterState);

        aRequiredCharacterState.addTransition(sRequiredCharacterState);

        sRequiredCharacterState.addTransition(eRequiredCharacterState);

        addStartedStates(Collections.singleton(cRequiredCharacterState));
    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) {
        return Optional.empty();
    }
}
