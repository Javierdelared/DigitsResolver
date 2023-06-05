package com.digits.resolver.utils;

import com.digits.resolver.configuration.ValidatorConfig;
import com.digits.resolver.exception.InvalidInputException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public class ValidatorUtils {

    private final ValidatorConfig validatorConfig;

    public ValidatorUtils(ValidatorConfig validatorConfig) {
        this.validatorConfig = validatorConfig;
    }

    private void validateNumbers(List<Integer> numbers) {
        if (numbers == null || numbers.size() < 2) {
            throw new InvalidInputException("Invalid list of numbers");
        }
        numbers.forEach(number -> {
            if (number > validatorConfig.getNumberMax()) {
                throw new InvalidInputException("Numbers cannot exceed " + validatorConfig.getNumberMax());
            }
        });
    }

    private void validateTarget(Integer target) {
        if (target == null) {
            throw new InvalidInputException("Invalid target");
        }
    }

    public void validateNumbersAndTarget(List<Integer> numbers, Integer target) {
        validateNumbers(numbers);
        validateTarget(target);
    }
}
