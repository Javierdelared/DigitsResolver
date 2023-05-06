package main.operations;

import java.util.List;

public class Sum implements Operator {
    @Override
    public Integer operate(Integer num1, Integer num2, List<HistoricOperation> historicOperations) {
        Integer result = num1 + num2;
        historicOperations.add(new HistoricOperation(num1, num2, result, this));
        return result;
    }

    @Override
    public String getOperator() {
        return "+";
    }
}
