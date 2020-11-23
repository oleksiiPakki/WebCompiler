package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.DeadLockException;
import io.teamdev.javaclasses.impl.abstracts.FSMFactory;
import io.teamdev.javaclasses.impl.abstracts.FiniteStateMachine;
import io.teamdev.javaclasses.impl.abstracts.State;
import io.teamdev.javaclasses.impl.runtime.Command;
import org.apache.log4j.Logger;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of fsm"{@link FiniteStateMachine}, calculating a math expression represented by
 * string
 * Examples : "2*(5+7*(4+1))+20"
 *
 *
 *         Shunting yard contains the result of execution of math expression with brackets
 *         considering priority
 */
public class ExpressionFiniteStateMachine extends FiniteStateMachine<List<Command>> {

    private static final Logger logger = Logger.getLogger(ExpressionFiniteStateMachine.class);

    public ExpressionFiniteStateMachine(FSMFactory factory) {

        if (logger.isInfoEnabled()) {
            logger.info("Expression Finite machine started" + "\n");
        }

        State<List<Command>> calculableState = new CalculableState(factory);
        State<List<Command>> binaryOperatorState = new ArithmeticBinaryOperatorState();

        calculableState.addTransition(binaryOperatorState);
        binaryOperatorState.addTransition(calculableState);

        addStartedStates(Collections.singleton(calculableState));

        if (logger.isInfoEnabled()) {
            logger.info("Expression Finite machine finished" + "\n");
        }
    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) throws DeadLockException {

        return expression(inputSequence);
    }

    public Optional<List<Command>> expression(CharacterIterator inputSequence) throws DeadLockException {

            List<Command> commands = new ArrayList<>();

            boolean isSuccess = run(inputSequence, commands);

            if (isSuccess) {
                return Optional.of(commands);
            }


        return Optional.empty();
    }
}
