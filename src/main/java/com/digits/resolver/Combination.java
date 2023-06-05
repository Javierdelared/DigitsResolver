package com.digits.resolver;

import com.digits.resolver.operations.Operation;

import java.util.ArrayList;
import java.util.List;

public class Combination {

    private final List<Operation> necessaryOperations;
    private final List<Integer> necessaryNumbers;

    public Combination() {
        this.necessaryOperations = new ArrayList<>();
        this.necessaryNumbers = new ArrayList<>();
    }

    public List<Operation> getNecessaryOperations() {
        return necessaryOperations;
    }

    public List<Integer> getNecessaryNumbers() {
        return necessaryNumbers;
    }

    public int compareTo(Combination combination) {
        return this.necessaryOperations.size() - combination.necessaryOperations.size();
    }

    @Override
    public String toString() {
        return "Combination{" +
                "necessaryOperations=" + necessaryOperations +
                ", necessaryNumbers=" + necessaryNumbers +
                '}';
    }
}
