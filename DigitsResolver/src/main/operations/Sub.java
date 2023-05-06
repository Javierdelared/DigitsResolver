package main.operations;

import main.Utils;

import java.util.List;

public class Sub implements Operation {
    @Override
    public int operate(int num1, int num2, List<String> operations) {
        if (num1 < num2) {
            // Swap
            num1 = num1 ^ num2 ^ (num2 = num1);
        }
        int result = num1 - num2;
        Utils.writeOperation(operations, num1, num2, result, "-");
        return result;
    }
}
