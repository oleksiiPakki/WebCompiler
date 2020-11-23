package io.teamdev.javaclasses.impl.runtime;

import java.util.List;

public interface Function {

    void execute(RuntimeEnvironment environment, List<ValueHolder> arguments) throws
                                                                              WrongCountOfArgumentsException;
}
