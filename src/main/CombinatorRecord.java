package main;

import main.operations.*;

import java.util.*;

public record CombinatorRecord(List<Integer> numbers, Integer target, List<Operator> operators) {

    public static class Builder {
        // Required parameters
        private final List<Integer> numbers;
        private final Integer target;
        // Optional parameters
        private List<Operator> operators = List.of(Sum.getInstance(), Sub.getInstance(),
                Mul.getInstance(), Div.getInstance());

        public Builder(List<Integer> numbers, Integer target) {
            this.numbers = numbers;
            this.target = target;
        }

        public Builder operators(List<Operator> operators) {
            this.operators = operators;
            return this;
        }

        public CombinatorRecord build() {
            return new CombinatorRecord(numbers, target, operators);
        }
    }
}
