package io.teamdev.javaclasses.impl.abstracts;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * General class for states. Define behaviour of states implementation
 *
 * @param <T>
 *         define the class, representing the result of execution on this state
 */
public abstract class State<T> {

    private final List<State<T>> transitions = new ArrayList<>();

    /**
     * Adding the possible next state
     *
     * @param state
     *         A state, that may following after current one
     */
    public void addTransition(State<T> state) {
        transitions.add(state);
    }

    /**
     * Getting all possible next states
     *
     * @return all states, those may following after this one
     */
    public List<State<T>> transitions() {
        return Collections.unmodifiableList(transitions);
    }

    /**
     * If the fsm may being on this state - perform needed operation
     *
     * @param inputSequence
     *         String, contains math expression
     * @param outputSequence
     *         The result after being on this state
     * @return Whether fsm being on this state or not
     */
    public abstract boolean accept(CharacterIterator inputSequence, T outputSequence) throws DeadLockException;

    /**
     * @return whether state may be finish or not
     */
    public abstract boolean mayBeFinish();

    public abstract boolean isLexeme();

}
