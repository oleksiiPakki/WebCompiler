package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.runtime.Command;
import io.teamdev.javaclasses.impl.runtime.ProgramExecutionException;
import io.teamdev.javaclasses.impl.runtime.RuntimeEnvironment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SwitchStructure {
    private boolean isExecutingStatements = true;
    private boolean isRequiredNumberHasBeenFounded = false;

    private final List<Double> searchValues = new ArrayList<>();
    private final List<Command> commandsToExecute = new ArrayList<>();

    public void addSearchValue(double searchValue) {
        searchValues.add(searchValue);
    }

    public void addCommandToExecute(Command command) {

        commandsToExecute.add(command);
    }

    public void execute(RuntimeEnvironment environment) throws ProgramExecutionException {


        for (Command command : commandsToExecute) {
            command.execute(environment);
        }

    }


    public boolean whetherExecutingStatements() {
        return isExecutingStatements;
    }

    public boolean isRequiredNumberHasBeenFounded(){
        return isRequiredNumberHasBeenFounded;
    }

    public void requiredNumberHasBeenFounded(){
        isRequiredNumberHasBeenFounded = true;
    }

    public void isExecutingStatements() {
        isExecutingStatements = true;
    }

    public void isNotExecutingStatements() {
        isExecutingStatements = false;
    }

    public List<Double> searchingValues() {
        return Collections.unmodifiableList(searchValues);
    }


}
