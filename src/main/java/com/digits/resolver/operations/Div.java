package com.digits.resolver.operations;

public class Div implements Operator {

    public static final Div INSTANCE = new Div();
    public static final String OPERATOR = "/";
    private Div() {}
    public static Div getInstance() {
        return INSTANCE;
    }
    @Override
    public Integer operate(Integer num1, Integer num2) {
        if (num1 < num2) {
            // Swap
            num1 = num1 ^ num2 ^ (num2 = num1);
        }
        if (isInValidDivision(num1, num2)) {
            return null;
        }
        return  num1 / num2;
    }

    private static boolean isInValidDivision(Integer num1, Integer num2) {
        return num2 == 0 || num1 % num2 != 0;
    }

    @Override
    public String getOperator() {
        return OPERATOR;
    }
}
