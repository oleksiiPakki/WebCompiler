package io.teamdev.javaclasses.impl.abstracts;

/**Throws when fsm can not move forward and current character may not be finish
 *
 */
public class DeadLockException extends IncorrectFormatOfExpressionException {


    public DeadLockException(String message){
        super(message);
    }

}
