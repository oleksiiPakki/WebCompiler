package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.DeadLockException;
import io.teamdev.javaclasses.impl.abstracts.State;
import org.apache.log4j.Logger;

import java.text.CharacterIterator;

/**
 * Implementation of State{@link State}, fsm being is when it finds a digit
 */
public class DigitCharacterState extends State<StringBuilder> {
    private static final Logger logger = Logger.getLogger(DigitCharacterState.class);

    private final boolean mayBeFinish;
    private final boolean isLexeme;

    DigitCharacterState() {
        this.mayBeFinish = true;
        this.isLexeme = false;
    }

    @Override
    public boolean isLexeme() {
        return isLexeme;
    }

    /**
     * @return whether number state may be finish or not
     */
    @Override
    public boolean mayBeFinish() {
        return mayBeFinish;
    }

    /**
     * Define digit and append it to result StringBuilder
     *
     * @param inputSequence  String, contains math expression
     * @param outputSequence StringBuilder contains digits of number in a math expression
     * @return true if current character is a digit or false if it is not
     */
    @Override
    public boolean accept(CharacterIterator inputSequence, StringBuilder outputSequence) throws
            DeadLockException {
        char currentCharacterOfSequence = inputSequence.current();

        if (Character.isDigit(currentCharacterOfSequence)) {

            if (logger.isTraceEnabled()) {
                logger.trace("Fsm in a DigitState: " + inputSequence.current() + "\n");
            }

            outputSequence.append(currentCharacterOfSequence);
            inputSequence.next();

            return true;
        }

        return false;
    }
}
