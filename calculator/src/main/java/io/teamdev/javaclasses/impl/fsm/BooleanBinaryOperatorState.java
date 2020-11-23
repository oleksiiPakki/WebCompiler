package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.State;
import io.teamdev.javaclasses.impl.runtime.*;

import java.text.CharacterIterator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BooleanBinaryOperatorState extends State<List<Command>> {

    private final boolean mayBeFinish;
    private final boolean isLexeme;

    private final Map<String, BooleanBinaryOperator> operators = new HashMap<>();

    BooleanBinaryOperatorState() {
        this.mayBeFinish = false;
        this.isLexeme = true;
    }

    {
        operators.put(">", new GreaterThanBinaryOperator(1));
        operators.put(">=", new GreaterThanOrEqualsToBinaryOperator(1));
        operators.put("<", new LessThanBinaryOperator(1));
        operators.put("<=", new LessThanOrEqualsToBinaryOperator(1));
        operators.put("==", new EqualsToBinaryOperator(1));
        operators.put("!=", new NotEqualsToBinaryOperator(1));

    }

    enum PossibleSignsOfBooleanOperators {

        GREATER_SIGN('>'),
        LESS_SIGN('<'),
        EQUALS_SIGN('='),
        NOT_EQUALS_SIGN('!');

        private final Character sign;

        PossibleSignsOfBooleanOperators(char sign) {
            this.sign = sign;
        }

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
    public boolean accept(CharacterIterator inputSequence, List<Command> outputSequence) {

        StringBuilder possibleNameOfOperator = new StringBuilder();

        while (isBooleanOperatorSign(inputSequence.current())) {

            possibleNameOfOperator.append(inputSequence.current());

            inputSequence.next();
        }

        Optional<BooleanBinaryOperator> currentOperator = defineBinaryOperator(
                possibleNameOfOperator.toString());

        if (currentOperator.isPresent()) {

            outputSequence.add(environment -> environment.topStack()
                                                         .pushOperator(currentOperator.get()));

            inputSequence.next();

            return true;
        }

        return false;
    }

    private Optional<BooleanBinaryOperator> defineBinaryOperator(
            String possibleNameOfOperator) {

        return operators.containsKey(possibleNameOfOperator) ? Optional.of(
                operators.get(possibleNameOfOperator)) : Optional.empty();
    }

    private boolean isBooleanOperatorSign(char currentSign) {

        for (PossibleSignsOfBooleanOperators possibleSign : PossibleSignsOfBooleanOperators.values()) {

            if (possibleSign.sign.equals(currentSign)) {

                return true;
            }
        }

        return false;
    }
}
