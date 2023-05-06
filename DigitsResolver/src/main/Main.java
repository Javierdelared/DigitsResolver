package main;

import main.operations.*;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final int[] numbers = new int[]{7,11,13,19,20,25};
        final int target = 401;
        List<String> historicOperations = new ArrayList<>();
        final List<Operation> operations = List.of(new Sum(), new Sub(), new Mult(), new Div());
        boolean result = combineNumbers(numbers, operations, target, historicOperations);
        if (result) {
            System.out.println("Solution reached:");
            historicOperations.forEach(System.out::println);
        }
    }

    private static boolean combineNumbers(int[] numbers, List<Operation> operations, int target, List<String> historicOperations) {
        if (numbers.length == 1) {
            if (numbers[0] == target) {
                return true;
            } else {
                return false;
            }
        }
        for (int i = 0; i <= numbers.length - 1; i++) {
            for (int j = i + 1; j <= numbers.length - 1; j++) {
                for (Operation operation : operations) {
                    int result = operation.operate(numbers[i], numbers[j], historicOperations);
                    if (result == target) {
                        return true;
                    } else {
                        int[] combinedNumbers = ArrayUtils.removeElement(numbers, numbers[i]);
                        combinedNumbers = ArrayUtils.removeElement(combinedNumbers, numbers[j]);
                        combinedNumbers = ArrayUtils.add(combinedNumbers, result);
                        boolean isValidCombination = combineNumbers(combinedNumbers, operations, target, historicOperations);
                        if (isValidCombination) {
                            return true;
                        }
                        historicOperations.remove(historicOperations.size() - 1);
                    }
                }
            }
        }
        return false;
    }
}