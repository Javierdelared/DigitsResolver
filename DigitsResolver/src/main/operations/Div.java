package main.operations;

import main.Utils;

import java.util.List;

public class Div implements Operation {
    @Override
    public int operate(int num1, int num2, List<String> operations) {
        if (num1 < num2) {
            // Swap
            num1 = num1 ^ num2 ^ (num2 = num1);
        }
        int result;
        if (num1 == 0 || num2 == 0 || num1 % num2 != 0) {
            // Division impossible
            result = 0;
        } else {
            result = num1 / num2;
        }
        Utils.writeOperation(operations, num1, num2, result, "/");
        return result;
    }
}
