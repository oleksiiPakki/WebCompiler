package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.DeadLockException;
import io.teamdev.javaclasses.impl.abstracts.FSMFactory;
import io.teamdev.javaclasses.impl.abstracts.FiniteStateMachine;
import io.teamdev.javaclasses.impl.abstracts.State;
import io.teamdev.javaclasses.impl.runtime.Command;
import io.teamdev.javaclasses.impl.runtime.FunctionStructure;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FunctionFiniteStateMachine extends FiniteStateMachine<FunctionStructure> {

    public FunctionFiniteStateMachine(FSMFactory factory) {

        State<FunctionStructure> nameState = new NameForFunctionState(false, false);
        State<FunctionStructure> openingBracketForFunctionState = new TransitState<>(false, true, '(');
        State<FunctionStructure> argumentState = new ArgumentState(factory);
        State<FunctionStructure> commaState = new TransitState<>(false, true, ',');
        State<FunctionStructure> closingBracketState = new TransitState<>(true, true, ')');

        nameState.addTransition(openingBracketForFunctionState);

        openingBracketForFunctionState.addTransition(argumentState);
        openingBracketForFunctionState.addTransition(closingBracketState);

        argumentState.addTransition(commaState);
        argumentState.addTransition(closingBracketState);

        commaState.addTransition(argumentState);

        addStartedStates(Collections.singletonList(nameState));

    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) throws DeadLockException {

        return function(inputSequence);
    }

    public Optional<List<Command>> function(CharacterIterator inputSequence) throws DeadLockException {

            FunctionStructure functionStructure = new FunctionStructure();

            boolean isSuccess = run(inputSequence, functionStructure);

            if (isSuccess) {

                List<Command> commands = new ArrayList<>();

                commands.add(functionStructure::execute);

                return Optional.of(commands);
            }


        return Optional.empty();
    }
}
