package com.digits.resolver.utils;

import com.digits.resolver.configuration.ValidatorConfig;
import com.digits.resolver.exception.InvalidInputException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ValidationUtilsTest {

    @InjectMocks
    ValidatorUtils validatorUtils;
    @Mock
    ValidatorConfig validatorConfig;

    @Test
    void validateNumbersAndTarget_ok() {
        // Given
        List<Integer> numbers = List.of(1, 2, 3);
        int target = 4;
        when(validatorConfig.getNumberMax()).thenReturn(30);
        // When
        try {
            validatorUtils.validateNumbersAndTarget(numbers, target);
        } catch (Exception e) {
            // Then
            assert false;
        }
    }

    @Test
    void validateNumbersAndTarget_numberListNull_returnInvalidInputException() {
        // Given
        int target = 4;
        // When
        RuntimeException exception = assertThrows(InvalidInputException.class, () ->
                validatorUtils.validateNumbersAndTarget(null, target));
        // Then
        assertEquals("Invalid list of numbers", exception.getMessage());
    }

    @Test
    void validateNumbersAndTarget_numberListTooSmall_returnInvalidInputException() {
        // Given
        List<Integer> numbers = List.of(1);
        int target = 4;
        // When
        RuntimeException exception = assertThrows(InvalidInputException.class, () ->
                validatorUtils.validateNumbersAndTarget(numbers, target));
        // Then
        assertEquals("Invalid list of numbers", exception.getMessage());
    }

    @Test
    void validateNumbersAndTarget_maxNumberExceeded_returnInvalidInputException() {
        // Given
        List<Integer> numbers = List.of(1, 2, 33);
        int target = 4;
        when(validatorConfig.getNumberMax()).thenReturn(30);
        // When
        RuntimeException exception = assertThrows(InvalidInputException.class, () ->
                validatorUtils.validateNumbersAndTarget(numbers, target));
        // Then
        assertEquals("Numbers cannot exceed 30", exception.getMessage());
    }

    @Test
    void validateNumbersAndTarget_targetNull_returnInvalidInputException() {
        // Given
        List<Integer> numbers = List.of(1, 2, 3);
        when(validatorConfig.getNumberMax()).thenReturn(30);
        // When
        RuntimeException exception = assertThrows(InvalidInputException.class, () ->
                validatorUtils.validateNumbersAndTarget(numbers, null));
        // Then
        assertEquals("Invalid target", exception.getMessage());
    }
}
