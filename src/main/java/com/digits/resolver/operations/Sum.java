package com.digits.resolver.operations;

public class Sum implements Operator {

    public static final Sum INSTANCE = new Sum();
    public static final String OPERATOR = "+";
    private Sum() {}

    public static Sum getInstance() {
        return INSTANCE;
    }
    @Override
    public Integer operate(Integer num1, Integer num2) {
        return num1 + num2;
    }

    @Override
    public String getOperator() {
        return OPERATOR;
    }
}
