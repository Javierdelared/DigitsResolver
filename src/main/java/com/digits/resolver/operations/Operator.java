package com.digits.resolver.operations;

public interface Operator {

    /**
     * Method to operate two numbers.
     *
     * @param num1  : First number.
     * @param num2  : Second number.
     *
     * @return Integer Result of the operation.
     */
    Integer operate(Integer num1, Integer num2);

    /**
     * Method to get the operator symbol.
     *
     * @return String with the operator symbol.
     */
    String getOperator();
}
