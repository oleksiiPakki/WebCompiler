package io.teamdev.javaclasses.impl.runtime;

import java.util.ArrayDeque;
import java.util.Deque;

public class ShuntingYard {


    private final Deque<ValueHolder> operands = new ArrayDeque<>();
    private final Deque<BinaryOperator> binaryOperators = new ArrayDeque<>();

    /**
     * @param operand
     *         operand to push in stack of operands
     */
    public void pushOperand(ValueHolder operand) {

        operands.push(operand);

    }

    /**
     * @param binaryOperator
     *         operator to push in stack of operators
     */

    public void pushOperator(BinaryOperator binaryOperator) {

        if (binaryOperators.peek() != null) {
            //if priority of current operator is not bigger,
            // than priority of peek operator in stack,
            // we execute last two operands with peek operator and push it back
            if (binaryOperators.peek()
                               .priority() >= binaryOperator.priority()) {

                popTopOperator();

                pushOperator(binaryOperator);

                return;
            }
        }

        binaryOperators.push(binaryOperator);
    }


    /**
     * @return result of execution
     */
    public ValueHolder getResult() {


        while (!binaryOperators.isEmpty()) {

            popTopOperator();
        }

        return operands.pop();
    }

    private void popTopOperator() {
        ValueHolder rightOperand = operands.pop();
        ValueHolder leftOperand = operands.pop();

        BinaryOperator binaryOperator = binaryOperators.pop();

        ValueHolder result = binaryOperator.execute(leftOperand, rightOperand);

        if (result != null) {
            operands.push(result);
        }
    }

}
