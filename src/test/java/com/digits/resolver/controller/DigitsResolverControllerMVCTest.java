package com.digits.resolver.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.digits.resolver.mapper.CombinationMapper;
import com.digits.resolver.service.DigitResolverService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;

@WebMvcTest(controllers = DigitsResolverController.class)
class DigitsResolverControllerMVCTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DigitsResolverController digitsResolverController;
    @Mock
    private DigitResolverService digitResolverService;
    @Mock
    private CombinationMapper combinationMapper;

    @Test
    void whenValidInput_thenReturns200() throws Exception {
        mockMvc.perform(get("/digits-resolver/best")
                        .param("numbers", "1,2,3,4")
                        .param("target", "24")
                        .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void whenInvalidTarget_thenReturns404() throws Exception {
        mockMvc.perform(get("/digits-resolver/best")
                        .param("numbers", "1,2,3,4")
                        .contentType("application/json"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void whenInvalidNumbers_thenReturns404() throws Exception {
        mockMvc.perform(get("/digits-resolver/best")
                        .param("target", "24")
                        .contentType("application/json"))
                .andExpect(status().is4xxClientError());
    }

}
