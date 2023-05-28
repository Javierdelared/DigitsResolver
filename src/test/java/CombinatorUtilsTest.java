import operations.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CombinatorUtilsTest {
    private static final int MIN_INT_NUMBER = 1;
    private static final int MAX_INT_NUMBER = 30;

    @Test
    public void calculateResult_ok_returnCorrectSolution() {
        // Given
        final List<Integer> numbers = List.of(7,9,13,19,20,23);
        final Integer target = 483;
        CombinatorRecord combinatorRecord = new CombinatorRecord.Builder(numbers, target).build();
        String expectedSolution = "Combination{necessaryOperations=[9 - 7 = 2, 19 + 2 = 21, 23 * 21 = 483], necessaryNumbers=[7, 9, 19, 23]}";
        // When
        Solution solution = CombinatorUtils.calculateResults(combinatorRecord, false);
        // Then
        assertNotNull(solution);
        assertEquals(expectedSolution, solution.getBestCombinationSolution().toString());
    }

    @Test
    public void calculateResult_randomData_returnCorrectSolution() {
        // Given
        List<Integer> numbers = generateRandomIntegerList();
        List<Operator> operators = List.of(Sum.getInstance(), Sub.getInstance(), Mul.getInstance());
        Integer target = generateTarget(numbers, operators);
        CombinatorRecord combinatorRecord = new CombinatorRecord.Builder(numbers, target).build();
        // When
        Solution solution = CombinatorUtils.calculateResults(combinatorRecord, false);
        // Then
        assertNotNull(solution);
        List<Operation> operations = solution.getBestCombinationSolution().getNecessaryOperations();
        List<Integer> validNumbers = new ArrayList<>(numbers);
        for (Operation operation : operations) {
            assertTrue(validNumbers.contains(operation.getNum1()));
            assertTrue(validNumbers.contains(operation.getNum2()));
            validNumbers.remove(operation.getNum1());
            validNumbers.remove(operation.getNum2());
            validNumbers.add(operation.getResult());
        }
        assertEquals(target, operations.get(operations.size() - 1).getResult());
    }

    private static Integer generateTarget(List<Integer> numbers, List<Operator> operators) {
        Integer int7 = getRandomOperator(operators).operate(numbers.get(0), numbers.get(1));
        Integer int8 = getRandomOperator(operators).operate(numbers.get(2), numbers.get(3));
        Integer int9 = getRandomOperator(operators).operate(numbers.get(4), numbers.get(5));
        int int10 = getRandomOperator(operators).operate(int7, int8);
        return getRandomOperator(operators).operate(int9, int10);
    }

    private static Operator getRandomOperator(List<Operator> operators) {
        return operators.get(generateRandom(0, operators.size() - 1));
    }

    private static List<Integer> generateRandomIntegerList() {
        int int1 = generateRandom(MIN_INT_NUMBER, MAX_INT_NUMBER);
        int int2 = generateRandom(MIN_INT_NUMBER, MAX_INT_NUMBER);
        int int3 = generateRandom(MIN_INT_NUMBER, MAX_INT_NUMBER);
        int int4 = generateRandom(MIN_INT_NUMBER, MAX_INT_NUMBER);
        int int5 = generateRandom(MIN_INT_NUMBER, MAX_INT_NUMBER);
        int int6 = generateRandom(MIN_INT_NUMBER, MAX_INT_NUMBER);
        return List.of(int1, int2, int3, int4, int5, int6);
    }

    private static int generateRandom(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1)) + min;
    }
}
