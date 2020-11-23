package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.State;
import org.apache.log4j.Logger;

import java.text.CharacterIterator;

public class SignCharacterState extends State<StringBuilder> {
    private static final Logger logger = Logger.getLogger(SignCharacterState.class);

    private final boolean mayBeFinish;
    private final boolean isLexeme;
    final private Character requiredCharacter;


    public SignCharacterState(boolean mayBeFinish, boolean isLexeme, Character requiredCharacter) {
        this.mayBeFinish = mayBeFinish;
        this.isLexeme = isLexeme;
        this.requiredCharacter = requiredCharacter;
    }

    @Override
    public boolean mayBeFinish() {
        return mayBeFinish;
    }

    @Override
    public boolean isLexeme() {
        return isLexeme;
    }

    @Override
    public boolean accept(CharacterIterator inputSequence, StringBuilder outputSequence) {

        Character currentCharacter = inputSequence.current();

        if (currentCharacter.equals(requiredCharacter)) {
            if (logger.isTraceEnabled()) {
                logger.trace("Fsm in a SignCharacterState:" + inputSequence.current() + "\n");

            }

            outputSequence.append(currentCharacter);
            inputSequence.next();

            return true;
        }

        return false;
    }
}
