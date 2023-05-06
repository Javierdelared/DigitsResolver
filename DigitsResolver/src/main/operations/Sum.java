package main.operations;

import main.Utils;

import java.util.List;

public class Sum implements Operation {
    @Override
    public int operate(int num1, int num2, List<String> operations) {
        int result = num1 + num2;
        Utils.writeOperation(operations, num1, num2, result, "+");
        return result;
    }
}
