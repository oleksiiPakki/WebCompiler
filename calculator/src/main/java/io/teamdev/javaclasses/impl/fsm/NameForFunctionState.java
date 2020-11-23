package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.DeadLockException;
import io.teamdev.javaclasses.impl.abstracts.State;
import io.teamdev.javaclasses.impl.runtime.*;

import java.text.CharacterIterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class NameForFunctionState extends State<FunctionStructure> {

    private final boolean mayBeFinish;
    private final boolean isLexeme;

    private final Map<String, Function> functions = new HashMap<>();

    public NameForFunctionState(boolean mayBeFinish, boolean isLexeme) {
        this.mayBeFinish = mayBeFinish;
        this.isLexeme = isLexeme;
    }

    {
        functions.put("min", new MinFunction());
        functions.put("max", new MaxFunction());
        functions.put("pow", new PowerFunction());
        functions.put("PI", new PiFunction());
        functions.put("sum", new SumFunction());
        functions.put("print", new PrintFunction());
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
    public boolean accept(CharacterIterator inputSequence, FunctionStructure outputSequence) throws DeadLockException {
        char currentCharacter = inputSequence.current();

        if (Character.isLetter(currentCharacter)) {


            StringBuilder possibleNameOfFunction = new StringBuilder();

            int positionBeforeParsingNameOfFunction = inputSequence.getIndex();

            boolean isSuccess = new NameFiniteStateMachine().run(inputSequence,
                    possibleNameOfFunction);

            if (isSuccess) {

                Optional<Function> currentFunction = defineFunction(
                        possibleNameOfFunction.toString());

                if (currentFunction.isPresent() && (inputSequence.current() == '(')) {

                    outputSequence.setFunction(currentFunction.get());

                } else {
                    inputSequence.setIndex(positionBeforeParsingNameOfFunction);

                    return false;
                }

            }

            return isSuccess;

        }

        return false;

    }


    private Optional<Function> defineFunction(String nameOfFunction) {

        return functions.containsKey(nameOfFunction) ? Optional.of(functions.get(nameOfFunction))
                : Optional.empty();
    }

}
