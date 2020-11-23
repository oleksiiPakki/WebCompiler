package io.teamdev.javaclasses.impl.runtime;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class RuntimeEnvironment {
    private boolean isInitializing = false;

    private final Deque<ShuntingYard> stack = new ArrayDeque<>();
    private final Memory memory = new Memory();
    private final StringBuilder output = new StringBuilder();


    public StringBuilder output() {
        return output;
    }

    public void startNewStack() {

        stack.push(new ShuntingYard());

    }

    public ShuntingYard closeTopStack() {

        return stack.pop();
    }

    public ShuntingYard topStack() {

        return stack.peek();
    }

    public void keepVariable(String variable) {
        memory.keepVariable(variable);
    }

    public void keepValue(ValueHolder value) {

        if (isInitializing) {

            memory.keepValue(value);

            isInitializing = false;
        }
    }

    public void isInitializing() {
        isInitializing = true;
    }

    public void initialize() {

        String variable = memory.topVariable();
        ValueHolder value = memory.topValue();

        memory.memory.put(variable, value);


    }

    public ValueHolder value(String variable) throws InitializationException {
        if (memory.memory.containsKey(variable)) {

            return memory.memory.get(variable);
        }

        throw new InitializationException("Variable " + variable + " has not been initialization");
    }





    private static class Memory {
        private final Deque<ValueHolder> values = new ArrayDeque<>();
        private final Deque<String> variables = new ArrayDeque<>();
        private final Map<String, ValueHolder> memory = new HashMap<>();

        private void keepVariable(String variableName) {
            variables.push(variableName);
        }

        private String topVariable() {
            return variables.pop();
        }

        private void keepValue(ValueHolder value) {
            values.push(value);
        }

        private ValueHolder topValue() {
            return values.pop();
        }



    }


}
