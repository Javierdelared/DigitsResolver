package com.digits.resolver.controller;

import com.digits.resolver.dto.CombinationDto;
import com.digits.resolver.mapper.CombinationMapper;
import com.digits.resolver.model.Combination;
import com.digits.resolver.service.DigitResolverService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DigitsResolverControllerTest {

    @InjectMocks
    private DigitsResolverController digitsResolverController;
    @Mock
    private DigitResolverService digitResolverService;
    @Mock
    private CombinationMapper combinationMapper;

    @Test
    void getBestSolution_ok_thenReturnsValidResponse() {
        // Given
        List<Integer> numbers = List.of(1, 2, 3, 4);
        int target = 24;
        Combination combination = mock(Combination.class);
        CombinationDto expectedCombinationDto = new CombinationDto();
        when(digitResolverService.getBestSolution(numbers, target)).thenReturn(combination);
        when(combinationMapper.toDto(combination)).thenReturn(expectedCombinationDto);
        // When
        CombinationDto result = digitsResolverController.getBestSolution(numbers, target);
        // Then
        assertEquals(expectedCombinationDto, result);
    }
}
