package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.DeadLockException;
import io.teamdev.javaclasses.impl.abstracts.FiniteStateMachine;
import io.teamdev.javaclasses.impl.abstracts.State;
import io.teamdev.javaclasses.impl.runtime.Command;
import io.teamdev.javaclasses.impl.runtime.DoubleValueHolder;
import org.apache.log4j.Logger;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NumberFiniteStateMachine extends FiniteStateMachine<StringBuilder> {

    private static final Logger logger = Logger.getLogger(NumberFiniteStateMachine.class);

    public NumberFiniteStateMachine() {

        if (logger.isInfoEnabled()) {
            logger.info("Number Finite machine started" + "\n");
        }

        State<StringBuilder> negativeNumberSignState = new SignCharacterState(false, false, '-');
        State<StringBuilder> integerDigitState = new DigitCharacterState();
        State<StringBuilder> decimalPointState = new SignCharacterState(false, false, '.');
        State<StringBuilder> decimalDigitState = new DigitCharacterState();

        negativeNumberSignState.addTransition(integerDigitState);

        integerDigitState.addTransition(integerDigitState);
        integerDigitState.addTransition(decimalPointState);

        decimalPointState.addTransition(decimalDigitState);
        decimalDigitState.addTransition(decimalDigitState);

        addStartedStates(Arrays.asList(negativeNumberSignState, integerDigitState));

        if (logger.isInfoEnabled()) {
            logger.info("Number Finite machine finished" + "\n");
        }

    }

    public Optional<List<Command>> number(CharacterIterator input) throws DeadLockException {
        List<Command> resultCommands = new ArrayList<>();

        StringBuilder number = new StringBuilder();

            boolean isSuccess = run(input, number);

            if (isSuccess) {
                double resultNumber = Double.parseDouble(number.toString());

                resultCommands.add(environment -> environment.topStack()
                                                             .pushOperand(new DoubleValueHolder(
                                                                     resultNumber)));

                return Optional.of(resultCommands);

            }


        return Optional.empty();
    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) throws DeadLockException {
        return number(inputSequence);
    }
}
