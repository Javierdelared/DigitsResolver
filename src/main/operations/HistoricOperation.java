package main.operations;

public class HistoricOperation {

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

    public HistoricOperation(Integer num1, Integer num2, Integer result, Operator operator) {
        this.num1 = num1;
        this.num2 = num2;
        this.result = result;
        this.operator = operator;
    }

    @Override
    public String toString() {
        return num1 + " " + operator.getOperator() + " " + num2 + " = " + result;
    }
}