package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.*;
import io.teamdev.javaclasses.impl.runtime.Command;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StatementFiniteStateMachine extends FiniteStateMachine<List<Command>> {

    public StatementFiniteStateMachine(FSMFactory factory) {
        State<List<Command>> initVariableState = new InitVariableState(factory);
        State<List<Command>> procedureState = new FunctionState(factory);

        addStartedStates(Arrays.asList(initVariableState, procedureState));
    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) throws DeadLockException {

        return statement(inputSequence);
    }

    public Optional<List<Command>> statement(CharacterIterator inputSequence) throws DeadLockException {
        List<Command> commands = new ArrayList<>();

        boolean isSuccess = run(inputSequence, commands);

        if (isSuccess){
            return Optional.of(commands);
        }

        return Optional.empty();
    }
}
