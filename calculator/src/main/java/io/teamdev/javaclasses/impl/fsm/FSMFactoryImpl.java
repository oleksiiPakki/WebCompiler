package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.Compiler;
import io.teamdev.javaclasses.impl.abstracts.FSMFactory;

import java.util.EnumMap;

public class FSMFactoryImpl implements FSMFactory {
    private final EnumMap<TypeFSM, Compiler> machines = new EnumMap<>(TypeFSM.class);

    public FSMFactoryImpl(){
        machines.put(TypeFSM.NUMBER, inputSequence -> {
            NumberFiniteStateMachine fsm = new NumberFiniteStateMachine();

            return fsm.number(inputSequence);
        });

        machines.put(TypeFSM.EXPRESSION, inputSequence -> {
            ExpressionFiniteStateMachine fsm = new ExpressionFiniteStateMachine(this);

            return fsm.expression(inputSequence);
        });

        machines.put(TypeFSM.EXPRESSION_WITH_BRACKETS, inputSequence ->{
            ExpressionWithBracketsFiniteStateMachine fsm = new ExpressionWithBracketsFiniteStateMachine(this);

            return fsm.expressionWithBrackets(inputSequence);
        });

        machines.put(TypeFSM.FUNCTION, inputSequence -> {
            FunctionFiniteStateMachine fsm = new FunctionFiniteStateMachine(this);

            return fsm.function(inputSequence);
        });

        machines.put(TypeFSM.CALCULABLE, inputSequence -> {
            CalculableFiniteStateMachine fsm = new CalculableFiniteStateMachine(this);

            return fsm.calculable(inputSequence);
        });

        machines.put(TypeFSM.BOOLEAN_EXPRESSION, inputSequence -> {
            BooleanExpressionFiniteStateMachine fsm = new BooleanExpressionFiniteStateMachine(this);

            return fsm.booleanExpression(inputSequence);
        });

        machines.put(TypeFSM.INITIALIZATION, inputSequence -> {
            InitVariableFiniteStateMachine fsm = new InitVariableFiniteStateMachine(this);

            return fsm.initVariable(inputSequence);
        });

        machines.put(TypeFSM.STATEMENT, inputSequence -> {
            StatementFiniteStateMachine fsm = new StatementFiniteStateMachine(this);

            return fsm.statement(inputSequence);
        });

    }

    @Override
    public Compiler create(TypeFSM typeFSM) {
        return machines.get(typeFSM);
    }
}
