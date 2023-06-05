package com.digits.resolver.operations;

import java.util.Objects;

public class Operation {

    private final Integer num1;
    private final Operator operator;
    private final Integer num2;
    private final Integer result;

    public Integer getNum1() {
        return num1;
    }
    public Operator getOperator() {
        return operator;
    }
    public Integer getNum2() {
        return num2;
    }
    public Integer getResult() {
        return result;
    }

    public Operation(Integer num1, Integer num2, Operator operator) {
        this.num1 = num1;
        this.num2 = num2;
        this.result = operator.operate(num1, num2);
        this.operator = operator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Operation operation = (Operation) o;
        return ((Objects.equals(num1, operation.num1) &&
                Objects.equals(num2, operation.num2)) ||
                (Objects.equals(num1, operation.num2) &&
                        Objects.equals(num2, operation.num1))) &&
                Objects.equals(operator, operation.operator) &&
                Objects.equals(result, operation.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(num1, operator, num2, result);
    }

    @Override
    public String toString() {
        int nHigh = num1;
        int nLow = num2;
        if (num1 < num2) {
            nHigh = nHigh ^ nLow ^ (nLow = nHigh);
        }
        return nHigh + " " + operator.getOperator() + " " + nLow + " = " + result;
    }
}
