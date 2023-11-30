package com.service.bearrecipes.controller.page;

import com.service.bearrecipes.config.security.SecurityConfiguration;
import com.service.bearrecipes.dto.ReceiptDTO;
import com.service.bearrecipes.model.*;
import com.service.bearrecipes.service.IngredientService;
import com.service.bearrecipes.service.ReceiptService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(IngredientPageController.class)
@Import(SecurityConfiguration.class)
public class IngredientPageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReceiptService receiptService;
    @MockBean
    private IngredientService ingredientService;
    

    @DisplayName("Получение страницы добавления")
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void getAddReceiptPagetTest() throws Exception {
        Mockito.when(receiptService.findById(1L)).thenReturn(getReceiptDTOsForTest());

        mockMvc.perform(get("/ingredient/addingredient/1")).andExpect(
                status().isOk()).andExpect(
                content().contentType("text/html;charset=UTF-8")).andExpect(
                content().string(containsString("testReceiptDTO"))).andExpect(
                content().string(containsString("111")));
    }

    @Test
    public void unauthorizedUserRedirectToLoginPageTest() throws Exception {
        String redirectUrl = "http://localhost/login";
        String headerLocation = "Location";

        assertEquals(redirectUrl, mockMvc.perform(get("/ingredient/addingredient/1"))
                .andExpect(status().is3xxRedirection()).andReturn().getResponse().getHeader(headerLocation));
    }

    private ReceiptDTO getReceiptDTOsForTest() {
        return new ReceiptDTO(1L, "testReceipt", new byte[0], "testReceipt", 111L,
                new Author(1L, "testAuthor", "testAuthor", new Country(1L, "testCountry")),
                new Country(1L, "testCountry"), List.of(new Ingredient(1L, "testIngredient",
                BigDecimal.valueOf(111), BigDecimal.valueOf(111), new Receipt(1L))),
                List.of(new StepInfo(1L, "testStepInfo", new byte[0], new Receipt(1L))));
    }
}
