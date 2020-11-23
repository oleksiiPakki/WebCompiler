package io.teamdev.javaclasses.impl.abstracts;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static java.util.Optional.empty;

/**
 * @param <T>
 *         define the class, representing the result of execution
 */
public abstract class FiniteStateMachine<T> implements Compiler {

    private Collection<State<T>> transitions = new ArrayList<>();

    /**
     * Registering possible start states for fsm
     *
     * @param states
     *         List of possible start states
     */
    protected void addStartedStates(Iterable<State<T>> states) {
        for (State<T> state : states) {
            transitions.add(state);
        }
    }

    /**
     * Execution of fsm
     *
     * @param inputSequence
     *         String, contains math expression
     * @param outputSequence
     *         result of execution of fsm
     * @throws DeadLockException
     *         in cases of incorrect format of math expression, such as :
     *         -missing operands of binary operators;
     *         -empty brackets;
     *         -incorrect brackets sequence;
     *         -empty math expression;
     */
    public boolean run(CharacterIterator inputSequence, T outputSequence)
            throws DeadLockException {

        Optional<State<T>> currentState = empty();

        while (true) {
            //if current state is present - define next possible states, else transitions are started states
            currentState.ifPresent(tState -> transitions = tState.transitions());

            //get next possible state after current
            char currentCharacter = inputSequence.current();

            if (currentState.isPresent() && currentState.get()
                                                        .isLexeme()) {
                if (Character.isWhitespace(currentCharacter)) {

                    while (Character.isWhitespace(inputSequence.current())) {

                        inputSequence.next();
                    }
                }
            }

            Optional<State<T>> nextState = stepForward(inputSequence, outputSequence, transitions);

            if (!nextState.isPresent()) {

                if (currentState.isPresent()) {

                    if (currentState.get()
                                    .mayBeFinish()) {

                        return true;

                    } else {
                        throw new DeadLockException("Deadlock on " + inputSequence.getIndex() + " position");

                    }
                }
                //if we can't step forward and current state may be finish, we finishing execution of fsm

                return false;
            }

            currentState = nextState;
        }

    }

    /**
     * @param inputSequence
     *         String, contains math expression
     * @param outputSequence
     *         Result of execution of fsm on current state
     * @param transitions
     *         possible transitions for current state
     * @return next state
     */
    private Optional<State<T>> stepForward(CharacterIterator inputSequence, T outputSequence,
                                           Collection<State<T>> transitions) throws DeadLockException {

        for (State<T> state : transitions) {
                if (state.accept(inputSequence, outputSequence)) {
                    return Optional.of(state);
                }
        }

        return Optional.empty();
    }

}
