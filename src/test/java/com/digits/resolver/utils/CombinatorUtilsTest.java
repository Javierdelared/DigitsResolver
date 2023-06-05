package com.digits.resolver.utils;

import com.digits.resolver.exception.ServiceException;
import com.digits.resolver.exception.SolutionNotFoundException;
import com.digits.resolver.exception.TrivialSolutionException;
import com.digits.resolver.model.Solution;
import com.digits.resolver.model.CombinatorRecord;
import com.digits.resolver.operations.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CombinatorUtilsTest {
    private static final int MIN_INT_NUMBER = 1;
    private static final int MAX_INT_NUMBER = 30;

    @InjectMocks
    private CombinatorUtils combinatorUtils;

    @Test
    public void calculateResult_ok_returnCorrectSolution() {
        // Given
        final List<Integer> numbers = List.of(7,9,13,19,20,23);
        final Integer target = 483;
        CombinatorRecord combinatorRecord = new CombinatorRecord.Builder(numbers, target).build();
        String expectedSolution = "Combination{necessaryOperations=[9 - 7 = 2, 19 + 2 = 21, 23 * 21 = 483], necessaryNumbers=[7, 9, 19, 23]}";
        // When
        Solution solution = combinatorUtils.calculateResults(combinatorRecord, false);
        // Then
        assertNotNull(solution);
        assertEquals(expectedSolution, solution.getBestCombinationSolution().toString());
    }

    @Test
    public void calculateResultFistHit_ok_returnCorrectSolution() {
        // Given
        final List<Integer> numbers = List.of(7,9,13,19,20,23);
        final Integer target = 483;
        CombinatorRecord combinatorRecord = new CombinatorRecord.Builder(numbers, target).build();
        String expectedSolution = "Combination{necessaryOperations=[23 * 7 = 161, 20 - 13 = 7, 9 + 7 = 16, 19 - 16 = 3, 161 * 3 = 483], necessaryNumbers=[7, 9, 13, 19, 20, 23]}";
        // When
        Solution solution = combinatorUtils.calculateResults(combinatorRecord, true);
        // Then
        assertNotNull(solution);
        assertEquals(expectedSolution, solution.getBestCombinationSolution().toString());
    }

    @Test
    public void calculateResult_noSolution_thenThrowSolutionNotFoundException() {
        // Given
        final List<Integer> numbers = List.of(1,2,3);
        final Integer target = 10;
        CombinatorRecord combinatorRecord = new CombinatorRecord.Builder(numbers, target).build();
        // When
        ServiceException exception = assertThrows(SolutionNotFoundException.class, () ->
                combinatorUtils.calculateResults(combinatorRecord, false));
        // Then
        assertEquals("Solution not found", exception.getMessage());
    }

    @Test
    public void calculateResult_targetInNumbers_returnNull() {
        // Given
        final List<Integer> numbers = List.of(1,2,3);
        final Integer target = 3;
        CombinatorRecord combinatorRecord = new CombinatorRecord.Builder(numbers, target).build();
        // When
        ServiceException exception = assertThrows(TrivialSolutionException.class, () ->
                combinatorUtils.calculateResults(combinatorRecord, false));
        // Then
        assertEquals("The solution is trivial", exception.getMessage());
    }

    @Test
    public void calculateResult_randomData_returnCorrectSolution() {
        // Given
        List<Integer> numbers = generateRandomIntegerList();
        List<Operator> operators = List.of(Sum.getInstance(), Sub.getInstance(), Mul.getInstance());
        int target = generateTarget(numbers, operators);
        CombinatorRecord combinatorRecord = new CombinatorRecord.Builder(numbers, target).build();
        // When
        Solution solution;
        try {
            solution = combinatorUtils.calculateResults(combinatorRecord, false);
        } catch (TrivialSolutionException e) {
            return ;
        }
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
        int int7 = getRandomOperator(operators).operate(numbers.get(0), numbers.get(1));
        int int8 = getRandomOperator(operators).operate(numbers.get(2), numbers.get(3));
        int int9 = getRandomOperator(operators).operate(numbers.get(4), numbers.get(5));
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
