package main.operations;

import java.util.List;

public class Div implements Operator {
    @Override
    public Integer operate(Integer num1, Integer num2, List<HistoricOperation> historicOperations) {
        if (num1 < num2) {
            // Swap
            num1 = num1 ^ num2 ^ (num2 = num1);
        }
        if (isInValidDivision(num1, num2)) {
            // Division impossible
            return null;
        }
        int result = num1 / num2;
        historicOperations.add(new HistoricOperation(num1, num2, result, this));
        return result;
    }

    private static boolean isInValidDivision(Integer num1, Integer num2) {
        return num1 == 0 || num2 == 0 || (num1 % num2 != 0 && num2 % num1 != 0);
    }

    @Override
    public String getOperator() {
        return "/";
    }
}
