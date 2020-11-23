package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.FiniteStateMachine;
import io.teamdev.javaclasses.impl.abstracts.State;
import io.teamdev.javaclasses.impl.runtime.Command;
import org.apache.log4j.Logger;

import java.text.CharacterIterator;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class NameFiniteStateMachine extends FiniteStateMachine<StringBuilder> {
    private static final Logger logger = Logger.getLogger(NameFiniteStateMachine.class);

    public NameFiniteStateMachine() {
        if (logger.isInfoEnabled()) {
            logger.info("Name finite machine is started\n");
        }

        State<StringBuilder> alphabeticCharacterState = new AlphabeticCharacterState();
        State<StringBuilder> digitCharacterState = new DigitCharacterState();

        alphabeticCharacterState.addTransition(alphabeticCharacterState);
        alphabeticCharacterState.addTransition(digitCharacterState);

        digitCharacterState.addTransition(digitCharacterState);

        addStartedStates(Collections.singleton(alphabeticCharacterState));
    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) {
        return Optional.empty();
    }

}
