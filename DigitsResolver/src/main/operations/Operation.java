package main.operations;

import java.util.List;

public interface Operation {
    int operate(int num1, int num2, List<String> operations);
}
