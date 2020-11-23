package io.teamdev.javaclasses.impl.runtime;

public interface Command {
    void execute(RuntimeEnvironment environment) throws ProgramExecutionException;

}
