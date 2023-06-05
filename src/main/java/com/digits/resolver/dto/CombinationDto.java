package com.digits.resolver.dto;

import java.util.ArrayList;
import java.util.List;

public class CombinationDto {

    private List<String> necessaryOperations;
    private String necessaryNumbers;

    public CombinationDto() {
        this.necessaryOperations = new ArrayList<>();
    }

    public List<String> getNecessaryOperations() {
        return necessaryOperations;
    }

    public String getNecessaryNumbers() {
        return necessaryNumbers;
    }

    public void setNecessaryOperations(List<String> necessaryOperations) {
        this.necessaryOperations = necessaryOperations;
    }

    public void setNecessaryNumbers(String necessaryNumbers) {
        this.necessaryNumbers = necessaryNumbers;
    }

    @Override
    public String toString() {
        return "CombinationDto{" +
                "necessaryOperations=" + necessaryOperations +
                ", necessaryNumbers='" + necessaryNumbers + '\'' +
                '}';
    }
}
