package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.State;
import io.teamdev.javaclasses.impl.runtime.*;
import org.apache.log4j.Logger;

import java.text.CharacterIterator;
import java.util.List;

/**
 * Implementation of {@link State}, fsm being in when we finding closed bracket after parameters of
 * function
 * <p>
 * <p>
 * Shunting yard{@link  ShuntingYard} contains intermediate result of execution of binary
 * operations,
 * considering priority of last ones
 */
public class ClosingBracketState extends State<List<Command>> {

    private static final Logger logger = Logger.getLogger(ClosingBracketState.class);

    private final boolean mayBeFinish;
    private final boolean isLexeme;
    private final Character requiredCharacter;

    ClosingBracketState(Character requiredCharacter) {
        this.mayBeFinish = true;
        this.isLexeme = true;
        this.requiredCharacter = requiredCharacter;
    }

    @Override
    public boolean isLexeme() {
        return isLexeme;
    }

    /**
     * @return whether closing bracket may be finish state or not
     */
    @Override
    public boolean mayBeFinish() {
        return mayBeFinish;
    }

    /**
     * pushing closing bracket into a Shunting yard {@link ShuntingYard}, if current character is
     * ')'
     *
     * @param inputSequence  String, contains math expression
     * @param outputSequence Shunting Yard,{@link ShuntingYard} consisting arguments of functions
     * @return true if current character is ')' or false if it is not
     */
    @Override
    public boolean accept(CharacterIterator inputSequence, List<Command> outputSequence) {

        Character currentCharacter = inputSequence.current();

        if (requiredCharacter.equals(currentCharacter)) {
            if (logger.isInfoEnabled()) {
                logger.info("fsm in a closing bracket for function state");
            }

            outputSequence.add(environment -> {

                ValueHolder resultHolder = environment.closeTopStack().getResult();

                double result = DoubleValueReader.readDouble(resultHolder);

                environment.topStack().pushOperand(new DoubleValueHolder(result));
            });

            inputSequence.next();

            return true;
        }

        return false;
    }
}
