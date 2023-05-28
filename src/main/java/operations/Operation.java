package operations;

public class Operation {

    private final Integer num1;
    private final Integer num2;
    private final Integer result;
    private final Operator operator;

    public Integer getNum1() {
        return num1;
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
    public String toString() {
        int nHigh = num1;
        int nLow = num2;
        if (num1 < num2) {
            nHigh = nHigh ^ nLow ^ (nLow = nHigh);
        }
        return nHigh + " " + operator.getOperator() + " " + nLow + " = " + result;
    }
}
