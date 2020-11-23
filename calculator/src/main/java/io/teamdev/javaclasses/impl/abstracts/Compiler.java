package io.teamdev.javaclasses.impl.abstracts;

import io.teamdev.javaclasses.impl.runtime.Command;

import java.text.CharacterIterator;
import java.util.List;
import java.util.Optional;

public interface Compiler {
     Optional<List<Command>> execute(CharacterIterator inputSequence) throws
                                                                            DeadLockException;
}
