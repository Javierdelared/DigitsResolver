package main;

import main.operations.HistoricOperations;
import main.operations.Operation;
import main.operations.Operator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class CombinatorUtils {

    /**
     * Logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(CombinatorRecord.class);

    /**
     * Method to calculate the solution from the combinator.
     *
     * @param combinatorRecord  : Combinator record.
     * @param firstHit          : Flag to stop the search when the first hit is found.
     *
     * @return Solution         : The solution.
     */
    public static Solution calculateResults(CombinatorRecord combinatorRecord, boolean firstHit) {
        if (combinatorRecord.numbers().contains(combinatorRecord.target())) {
            LOG.info("The target solution is already contained in the list of numbers");
            return null;
        }
        HistoricOperations historicOperations = new HistoricOperations();
        searchHits(combinatorRecord.numbers(), combinatorRecord.target(), combinatorRecord.operators(),
                historicOperations, firstHit);
        Solution solution = generateSolution(combinatorRecord, historicOperations, firstHit);
        if (solution.getBestCombinationSolution() == null) {
            return null;
        }
        return solution;
    }

    /**
     * Method to try all the different combinations of numbers and operators to find the target.
     * @param numbers               : List of numbers.
     * @param target                : Target to find.
     * @param operators             : List of operators.
     * @param historicOperations    : List of historic operations.
     * @param firstHit              : Flag to stop the search when the first hit is found.
     *
     * @return boolean              : True if the target is found with the fie¡rst hit flag activated, false otherwise.
     */
    private static boolean searchHits(List<Integer> numbers, Integer target, List<Operator> operators,
                                      HistoricOperations historicOperations, boolean firstHit) {
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i + 1; j < numbers.size(); j++) {
                for (Operator operator : operators) {
                    Operation operation = new Operation(numbers.get(i), numbers.get(j), operator);
                    historicOperations.addOperationToCurrentPath(operation);
                    Integer result = operation.getResult();
                    if (Objects.equals(result, target) && firstHit) {
                        return true;
                    }
                    if (canContinueRecursion(numbers, target, result)) {
                        List<Integer> nextNumbers = updateCombination(numbers, i, j, result);
                        if (searchHits(nextNumbers, target, operators, historicOperations, firstHit) && firstHit) {
                            return true;
                        }
                    }
                    historicOperations.addNewPath();
                }
            }
        }
        return false;
    }

    /**
     * Method to check if the recursion can continue.
     * @param numbers   : List of numbers.
     * @param target    : Target to find.
     * @param result    : Result of the operation.
     * @return boolean  : True if the recursion can continue, false otherwise.
     */
    private static boolean canContinueRecursion(List<Integer> numbers, Integer target, Integer result) {
        return result != null && !Objects.equals(result, target) && numbers.size() > 2;
    }

    /**
     * Method to generate the solution of combinations of operations.
     *
     * @param combinatorRecord      : Combinator record.
     * @param historicOperations    : Historic operations.
     * @param firstHit              : Flag to stop the search when the first hit is found.
     *
     * @return Solution             : The solution.
     */
    private static Solution generateSolution(CombinatorRecord combinatorRecord, HistoricOperations historicOperations,
                                             boolean firstHit) {
        Solution solution = new Solution();
        for (List<Operation> operations : historicOperations.getHistoricOperationList()) {
            Combination combination = new Combination();
            boolean validSolution = populateCombination(combination, new ArrayList<>(combinatorRecord.numbers()),
                    new ArrayList<>(operations), combinatorRecord.target());
            if (validSolution) {
                solution.addCombination(combination);
            }
        }
        solution.calculateBestCombination();
        solution.setFirstHit(firstHit);
        return solution;
    }

    /**
     * Method to populate the solution.
     *
     * @param combination       : Combination to populate.
     * @param numbersLeft       : List of numbers left.
     * @param operationsLeft    : List of operations left.
     * @param target            : Target to find.
     *
     * @return boolean          : True if the combination is valid, false otherwise.
     */
    private static boolean populateCombination(Combination combination, List<Integer> numbersLeft,
                                               List<Operation> operationsLeft, Integer target) {
        Operation operation = operationsLeft.stream()
                .filter(h -> Objects.equals(h.getResult(), target)).findFirst().orElse(null);
        if (operation == null) {
            if (numbersLeft.contains(target)) {
                numbersLeft.remove(target);
                combination.getNecessaryNumbers().add(target);
                return true;
            } else {
                return false;
            }
        } else {
            operationsLeft.remove(operation);
            combination.getNecessaryOperations().add(operation);
            return populateCombination(combination, numbersLeft, operationsLeft, operation.getNum1()) &&
                    populateCombination(combination, numbersLeft, operationsLeft, operation.getNum2());
        }
    }

    /**
     * Method to update the list of numbers.
     *
     * @param numbers   : List of numbers.
     * @param i         : Index of the first number to remove.
     * @param j         : Index of the second number to remove.
     * @param result    : Number to add
     *
     * @return List     : List of numbers updated.
     */
    private static List<Integer> updateCombination(List<Integer> numbers, int i, int j, Integer result) {
        List<Integer> combinedNumbers = new ArrayList<>(numbers);
        combinedNumbers.remove(numbers.get(i));
        combinedNumbers.remove(numbers.get(j));
        combinedNumbers.add(result);
        return combinedNumbers;
    }

    /**
     * Method to log the solution.
     *
     * @param solution  : Solution to log.
     */
    public static void logSolution(Solution solution) {
        if(solution == null) {
            LOG.warn("No solution found");
            return;
        }
        if(solution.isFirstHit()) {
            LOG.info("First hit solution:");
        } else {
            LOG.info("Number of solutions: {}", solution.getCombinationSolutions().size());
            LOG.info("Solution with less operations:");
        }
        solution.getBestCombinationSolution().getNecessaryOperations().forEach(o -> LOG.info(o.toString()));
        LOG.info("Necessary numbers: {}", solution.getBestCombinationSolution().getNecessaryNumbers());
    }
}
