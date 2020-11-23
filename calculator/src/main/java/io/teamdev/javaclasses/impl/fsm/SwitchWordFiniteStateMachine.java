package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.FiniteStateMachine;
import io.teamdev.javaclasses.impl.abstracts.State;
import io.teamdev.javaclasses.impl.runtime.Command;

import java.text.CharacterIterator;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class SwitchWordFiniteStateMachine extends FiniteStateMachine<StringBuilder> {

    public SwitchWordFiniteStateMachine() {

        State<StringBuilder> sRequiredCharacterState = new TransitState<>(false, false, 's');
        State<StringBuilder> wRequiredCharacterState = new TransitState<>(false, false, 'w');
        State<StringBuilder> iRequiredCharacterState = new TransitState<>(false, false, 'i');
        State<StringBuilder> tRequiredCharacterState = new TransitState<>(false, false, 't');
        State<StringBuilder> cRequiredCharacterState = new TransitState<>(false, false, 'c');
        State<StringBuilder> hRequiredCharacterState = new TransitState<>(true, false, 'h');

        sRequiredCharacterState.addTransition(wRequiredCharacterState);

        wRequiredCharacterState.addTransition(iRequiredCharacterState);

        iRequiredCharacterState.addTransition(tRequiredCharacterState);

        tRequiredCharacterState.addTransition(cRequiredCharacterState);

        cRequiredCharacterState.addTransition(hRequiredCharacterState);


        addStartedStates(Collections.singleton(sRequiredCharacterState));
    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) {
        return Optional.empty();
    }
}
