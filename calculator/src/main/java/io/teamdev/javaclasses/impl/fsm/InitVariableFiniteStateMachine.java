package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.*;
import io.teamdev.javaclasses.impl.runtime.Command;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class InitVariableFiniteStateMachine extends FiniteStateMachine<List<Command>> {

    public InitVariableFiniteStateMachine(FSMFactory factory) {

        State<List<Command>> nameForInitState = new NameForVariableState(false, true);
        State<List<Command>> assignmentCharacterState = new AssignmentCharacterState('=');
        State<List<Command>> expressionState = new BooleanExpressionState(true, factory);

        nameForInitState.addTransition(assignmentCharacterState);
        assignmentCharacterState.addTransition(expressionState);

        addStartedStates(Collections.singleton(nameForInitState));
    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) throws DeadLockException {
        return initVariable(inputSequence);
    }

    public Optional<List<Command>> initVariable(CharacterIterator inputSequence) throws DeadLockException {

            List<Command> commands = new ArrayList<>();

            boolean isSuccess = run(inputSequence, commands);

            if (isSuccess) {
                return Optional.of(commands);
            }


        return Optional.empty();
    }
}
