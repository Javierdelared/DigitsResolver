package main;

import java.util.List;

public class Utils {

    public static void writeOperation(List<String> operations, int num1, int num2, int result, String operator) {
        operations.add(num1 + " " + operator + " " + num2 + " = " + result);
    }
}
