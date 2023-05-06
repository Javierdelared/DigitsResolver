package main;

import main.operations.*;

import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Combiner {

    private static final Logger LOG = LoggerFactory.getLogger(Combiner.class);

    private final List<Integer> numbers;
    private final Integer target;
    private final List<Operator> operators;
    private final List<HistoricOperation> historicOperations;
    private final List<HistoricOperation> necessaryOperations = new ArrayList<>();
    private final List<Integer> necessaryNumbers = new ArrayList<>();
    private List<Integer> numbersLeft;

    public Combiner(List<Integer> numbers, Integer target, List<Operator> operators,
                    List<HistoricOperation> historicOperations) {
        this.numbers = numbers;
        this.target = target;
        this.operators = operators;
        this.historicOperations = historicOperations;
    }

    public static class Builder {
        // Required parameters
        private final List<Integer> numbers;
        private final Integer target;
        // Optional parameters
        private List<Operator> operators = List.of(new Sum(), new Sub(), new Mul(), new Div());
        private List<HistoricOperation> historicOperations = new ArrayList<>();

        public Builder(List<Integer> numbers, Integer target) {
            this.numbers = numbers;
            this.target = target;
        }
        public Builder operators(List<Operator> operators) {
            this.operators = operators;
            return this;
        }

        public Builder historicOperations(List<HistoricOperation> historicOperations) {
            this.historicOperations = historicOperations;
            return this;
        }

        public Combiner build() {
            return new Combiner(numbers, target, operators, historicOperations);
        }
    }

    public boolean combine() {
        if (numbers.size() == 1) {
            return Objects.equals(numbers.get(0), target);
        }
        for (int i = 0; i <= numbers.size() - 1; i++) {
            for (int j = i + 1; j <= numbers.size() - 1; j++) {
                for (Operator operator : operators) {
                    Integer result = operator.operate(numbers.get(i), numbers.get(j), historicOperations);
                    if (result == null) {
                        // Case division impossible
                        continue;
                    }
                    if (Objects.equals(result, target)) {
                        return true;
                    } else {
                        List<Integer> combinedNumbers = updateCombination(i, j, result);
                        Combiner combiner = new Builder(combinedNumbers, target)
                                .operators(operators).historicOperations(historicOperations).build();
                        if (combiner.combine()) {
                            return true;
                        }
                        historicOperations.remove(historicOperations.size() - 1);
                    }
                }
            }
        }
        return false;
    }

    public void logResult() {
        if (numbers.contains(target)) {
            LOG.info("The target solution is already contained in the list of numbers");
            return ;
        }
        if (combine()) {
            LOG.info("Solution reached");
            cleanResults();
            LOG.info("Operations:");
            necessaryOperations.forEach(System.out::println);
            necessaryNumbers.sort(Integer::compareTo);
            LOG.info("Necessary numbers: {}", necessaryNumbers);
            return ;
        }
        LOG.warn("The solution is not reachable");
    }

    private void cleanResults() {
        LOG.info("Cleaning operation results");
        numbersLeft = new ArrayList<>(numbers);
        addTargetOperations(target);
        Collections.reverse(necessaryOperations);
    }

    private void addTargetOperations(Integer target) {
        if (numbersLeft.contains(target)) {
            numbersLeft.remove(target);
            necessaryNumbers.add(target);
        } else {
            HistoricOperation historicOperation = getHistoricOperation(target);
            necessaryOperations.add(historicOperation);
            addTargetOperations(historicOperation.getNum1());
            addTargetOperations(historicOperation.getNum2());
        }
    }

    private HistoricOperation getHistoricOperation(Integer target) {
        Optional<HistoricOperation> historicOperationOptional = historicOperations.stream()
                .filter(h -> Objects.equals(h.getResult(), target)).findFirst();
        if (historicOperationOptional.isEmpty()) {
            // TODO: Create custom exception.
            throw new RuntimeException();
        }
        return historicOperationOptional.get();
    }

    private List<Integer> updateCombination(int i, int j, Integer result) {
        List<Integer> combinedNumbers = new ArrayList<>(numbers);
        combinedNumbers.remove(numbers.get(i));
        combinedNumbers.remove(numbers.get(j));
        combinedNumbers.add(result);
        return combinedNumbers;
    }
}
