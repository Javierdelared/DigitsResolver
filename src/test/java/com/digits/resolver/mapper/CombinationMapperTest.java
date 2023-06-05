package com.digits.resolver.mapper;

import com.digits.resolver.dto.CombinationDto;
import com.digits.resolver.model.Combination;
import com.digits.resolver.operations.Mul;
import com.digits.resolver.operations.Operation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class CombinationMapperTest {

    @InjectMocks
    private CombinationMapper combinationMapper;

    @Test
    void toDto_ok_returnCombinationDto() {
        // Given
        Combination combination = new Combination();
        Operation operation = new Operation(3,2, Mul.getInstance());
        combination.getNecessaryOperations().add(operation);
        combination.getNecessaryNumbers().add(2);
        combination.getNecessaryNumbers().add(3);
        // When
        CombinationDto result = combinationMapper.toDto(combination);
        // Then
        assertEquals(1, result.getNecessaryOperations().size());
        assertEquals("3 * 2 = 6", result.getNecessaryOperations().get(0));
        assertEquals("[2, 3]", result.getNecessaryNumbers());
    }

    @Test
    void toDto_nullCombination_returnNull() {
        // Given
        // When
        CombinationDto result = combinationMapper.toDto(null);
        // Then
        assertNull(result);
    }
}
