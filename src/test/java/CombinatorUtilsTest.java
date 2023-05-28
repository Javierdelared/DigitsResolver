import operations.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CombinatorUtilsTest {

    @Test
    public void calculateResult_ok_returnCorrectSolution() {
        // Given
        final List<Integer> numbers = List.of(7,9,13,19,20,23);
        final Integer target = 483;
        CombinatorRecord combinatorRecord = new CombinatorRecord.Builder(numbers, target).build();
        String expectedSolution = "Combination{necessaryOperations=[7 - 9 = 2, 19 + 2 = 21, 23 * 21 = 483], necessaryNumbers=[7, 9, 19, 23]}";
        // When
        Solution solution = CombinatorUtils.calculateResults(combinatorRecord, false);
        // Then
        assertNotNull(solution);
        assertEquals(expectedSolution, solution.getBestCombinationSolution().toString());
    }

    @Test
    public void calculateResult_randomData_returnCorrectSolution() {
        // Given
        List<Integer> numbers = generateRandomIntegerList(30);
        Integer target = generateTarget(numbers);
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

    private static Integer generateTarget(List<Integer> numbers) {
        List<Operator> operators = List.of(Sum.getInstance(), Sub.getInstance(),
                Mul.getInstance());
        int operations1 = generateRandom(0,3);
        int operations2 = generateRandom(0,3);
        int operations3 = generateRandom(0,3);
        Operator operator1 = operators.get(operations1);
        Operator operator2 = operators.get(operations2);
        Operator operator3 = operators.get(operations3);
        Integer int7 = operator1.operate(numbers.get(0), numbers.get(1));
        Integer int8 = operator2.operate(numbers.get(2), numbers.get(3));
        Integer int9 = operator3.operate(numbers.get(4), numbers.get(5));
        int operations4 = generateRandom(0,3);
        int operations5 = generateRandom(0,3);
        Operator operator4 = operators.get(operations4);
        Operator operator5 = operators.get(operations5);
        int int10 = operator4.operate(int7, int8);
        return operator5.operate(int10, int9);
    }

    private static List<Integer> generateRandomIntegerList(int max) {
        int int1 = generateRandom(1, max);
        int int2 = generateRandom(1, max);
        int int3 = generateRandom(1, max);
        int int4 = generateRandom(1, max);
        int int5 = generateRandom(1, max);
        int int6 = generateRandom(1, max);
        return List.of(int1, int2, int3, int4, int5, int6);
    }

    private static int generateRandom(int min, int max) {
        return (int) (Math.random() * (max - min)) + min;
    }
}
