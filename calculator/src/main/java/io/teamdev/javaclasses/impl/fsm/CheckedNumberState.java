package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.DeadLockException;
import io.teamdev.javaclasses.impl.abstracts.State;

import java.text.CharacterIterator;

public class CheckedNumberState extends State<SwitchStructure> {
    private final boolean mayBeFinish;
    private final boolean isLexeme;

    public CheckedNumberState(boolean mayBeFinish, boolean isLexeme) {
        this.mayBeFinish = mayBeFinish;
        this.isLexeme = isLexeme;
    }

    @Override
    public boolean isLexeme() {
        return isLexeme;
    }

    @Override
    public boolean mayBeFinish() {
        return mayBeFinish;
    }

    @Override
    public boolean accept(CharacterIterator inputSequence, SwitchStructure outputSequence) throws DeadLockException {

        StringBuilder nameOfCurrentNumber = new StringBuilder();

        boolean isSuccess = new NumberFiniteStateMachine().run(inputSequence, nameOfCurrentNumber);

        double currentNumber = Double.parseDouble(nameOfCurrentNumber.toString());

        if (isSuccess) {

            outputSequence.addCommandToExecute(environment -> {

                    boolean isSearching = isSearching(outputSequence, currentNumber);

                    if (isSearching) {

                        outputSequence.isExecutingStatements();

                    } else {

                        outputSequence.isNotExecutingStatements();
                    }
                });
        }


        return isSuccess;
    }

    private boolean isSearching(SwitchStructure structure, Double currentNumber){

        for (Double oneOfSearchingNumber : structure.searchingValues()){

            if(currentNumber.equals(oneOfSearchingNumber)){
                return true;
            }
        }

        return false;
    }
}
