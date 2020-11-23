package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.FiniteStateMachine;
import io.teamdev.javaclasses.impl.abstracts.State;
import io.teamdev.javaclasses.impl.runtime.Command;

import java.text.CharacterIterator;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class DefaultWordFiniteStateMachine extends FiniteStateMachine<StringBuilder> {
    public DefaultWordFiniteStateMachine() {
        State<StringBuilder> dRequiredCharacterState = new TransitState<>(false, false, 'd');
        State<StringBuilder> eRequiredCharacterState = new TransitState<>(false, false, 'e');
        State<StringBuilder> fRequiredCharacterState = new TransitState<>(false, false, 'f');
        State<StringBuilder> aRequiredCharacterState = new TransitState<>(false, false, 'a');
        State<StringBuilder> uRequiredCharacterState = new TransitState<>(false, false, 'u');
        State<StringBuilder> lRequiredCharacterState = new TransitState<>(false, false, 'l');
        State<StringBuilder> tRequiredCharacterState = new TransitState<>(true, false, 't');

        dRequiredCharacterState.addTransition(eRequiredCharacterState);

        eRequiredCharacterState.addTransition(fRequiredCharacterState);

        fRequiredCharacterState.addTransition(aRequiredCharacterState);

        aRequiredCharacterState.addTransition(uRequiredCharacterState);

        uRequiredCharacterState.addTransition(lRequiredCharacterState);

        lRequiredCharacterState.addTransition(tRequiredCharacterState);

        addStartedStates(Collections.singletonList(dRequiredCharacterState));
    }


    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) {
        return Optional.empty();
    }
}
