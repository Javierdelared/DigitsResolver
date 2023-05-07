package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    private final List<Combination> combinations = new ArrayList<>();
    private Combination bestCombination;
    private boolean firstHit;

    public List<Combination> getCombinationSolutions() {
        return combinations;
    }

    public Combination getBestCombinationSolution() {
        return bestCombination;
    }
    public boolean isFirstHit() {
        return firstHit;
    }

    public void setFirstHit(boolean firstHit) {
        this.firstHit = firstHit;
    }

    /**
     * Adds a combination to the list of combinations and sorts its fields.
     *
     * @param combination   : Combination to add.
     */
    public void addCombination(Combination combination) {
        // Sort lists correctly
        Collections.reverse(combination.getNecessaryOperations());
        combination.getNecessaryNumbers().sort(Integer::compareTo);
        combinations.add(combination);
    }

    /**
     * Calculates the best combination and populates the field bestCombination.
     */
    public void calculateBestCombination() {
        combinations.stream().min(Combination::compareTo)
                .ifPresent(x -> bestCombination = x);
    }

    @Override
    public String toString() {
        return "CombinationSolutionCollection{" +
                "combinationSolutions=" + combinations +
                ", bestCombinationSolution=" + bestCombination +
                '}';
    }
}
