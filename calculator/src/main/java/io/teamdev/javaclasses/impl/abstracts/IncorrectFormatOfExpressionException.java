package io.teamdev.javaclasses.impl.abstracts;

/**
 * Class, represented the exception, may be thrown in cases of incorrect format of math expression, such as :
 *      *                                                                    -missing operands of binary operators;
 *      *                                                                    -empty brackets;
 *      *                                                                    -incorrect brackets sequence;
 *      *                                                                    -empty math expression;
 *      *
 *
 */
public class IncorrectFormatOfExpressionException extends Exception {


    public IncorrectFormatOfExpressionException(String message) {
        super(message);
    }

}
