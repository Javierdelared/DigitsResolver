package main.operations;

import java.util.List;

public class Sub implements Operator {
    @Override
    public Integer operate(Integer num1, Integer num2, List<HistoricOperation> historicOperations) {
        if (num1 < num2) {
            // Swap
            num1 = num1 ^ num2 ^ (num2 = num1);
        }
        Integer result = num1 - num2;
        historicOperations.add(new HistoricOperation(num1, num2, result, this));
        return result;
    }

    @Override
    public String getOperator() {
        return "-";
    }
}
