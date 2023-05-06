package main.operations;

import java.util.List;

public interface Operator {
    Integer operate(Integer num1, Integer num2, List<HistoricOperation> historicOperations);
    String getOperator();
}
