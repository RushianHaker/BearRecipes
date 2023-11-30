package com.service.bearrecipes.controller.page;

import com.service.bearrecipes.config.security.SecurityConfiguration;
import com.service.bearrecipes.dto.ReceiptDTO;
import com.service.bearrecipes.model.*;
import com.service.bearrecipes.service.AuthorService;
import com.service.bearrecipes.service.CountryService;
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
@WebMvcTest(ReceiptPageController.class)
@Import(SecurityConfiguration.class)
public class ReceiptPageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReceiptService receiptService;
    @MockBean
    private AuthorService authorService;
    @MockBean
    private CountryService countryService;


    private final static long TEST_RECEIPT_ID = 1L;

    @DisplayName("Получение страницы со списком рецептов")
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void getAllReceiptListPageTest() throws Exception {
        Mockito.when(receiptService.findAll()).thenReturn(getReceiptForTest());

        mockMvc.perform(get("/receipt")).andExpect(status().isOk()).andExpect(
                content().contentType("text/html;charset=UTF-8")).andExpect(
                content().string(containsString("<td> <a href=\\\"/receipt/inforeceipt/\" + receipt.id + \"\\\"> <button type=\\\"button\\\">Info</button> </td>")));
    }

    @DisplayName("Получение страницы добавления")
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void getAddReceiptPageTest() throws Exception {
        mockMvc.perform(get("/receipt/addreceipt")).andExpect(
                status().isOk()).andExpect(
                content().contentType("text/html;charset=UTF-8")).andExpect(
                content().string(containsString("<input id=\"country-input\" name=\"country-input\" type=\"text\"/>")));
    }

    @DisplayName("Получение страницы редактирования")
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void getEditReceiptPageTest() throws Exception {
        Mockito.when(receiptService.findById(TEST_RECEIPT_ID)).thenReturn(getReceiptDTOsForTest());

        mockMvc.perform(get("/receipt/editreceipt/1")).andExpect(
                status().isOk()).andExpect(
                content().contentType("text/html;charset=UTF-8")).andExpect(
                content().string(containsString("testReceiptDTO"))).andExpect(
                content().string(containsString("testAuthorReceiptDTO"))).andExpect(
                content().string(containsString("dinnerReceiptDTO")));
    }

    @DisplayName("Получение страницы полной информации по рецепту")
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void getInfoReceiptPageTest() throws Exception {
        Mockito.when(receiptService.findById(TEST_RECEIPT_ID)).thenReturn(getReceiptDTOsForTest());

        mockMvc.perform(get("/receipt/inforeceipt/1")).andExpect(
                status().isOk()).andExpect(
                content().contentType("text/html;charset=UTF-8")).andExpect(
                content().string(containsString("testReceiptDTO"))).andExpect(
                content().string(containsString("testAuthorReceiptDTO")));
    }

    @DisplayName("Получение страницы удаления рецептов")
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void getDeleteReceiptPageTest() throws Exception {
        Mockito.when(receiptService.findById(TEST_RECEIPT_ID)).thenReturn(getReceiptDTOsForTest());

        mockMvc.perform(get("/receipt/delreceipt/1")).andExpect(
                status().isOk()).andExpect(
                content().contentType("text/html;charset=UTF-8")).andExpect(
                content().string(containsString("testReceiptDTO"))).andExpect(
                content().string(containsString("testAuthorReceiptDTO")));
    }

    @Test
    public void unauthorizedUserRedirectToLoginPageTest() throws Exception {
        String redirectUrl = "http://localhost/login";
        String headerLocation = "Location";

        assertEquals(redirectUrl, mockMvc.perform(get("/receipt"))
                .andExpect(status().is3xxRedirection()).andReturn().getResponse().getHeader(headerLocation));
        assertEquals(redirectUrl, mockMvc.perform(get("/receipt/addreceipt"))
                .andExpect(status().is3xxRedirection()).andReturn().getResponse().getHeader(headerLocation));
        assertEquals(redirectUrl, mockMvc.perform(get("/receipt/editreceipt/1"))
                .andExpect(status().is3xxRedirection()).andReturn().getResponse().getHeader(headerLocation));
        assertEquals(redirectUrl, mockMvc.perform(get("/receipt/inforeceipt/1"))
                .andExpect(status().is3xxRedirection()).andReturn().getResponse().getHeader(headerLocation));
        assertEquals(redirectUrl, mockMvc.perform(get("/receipt/delreceipt/1"))
                .andExpect(status().is3xxRedirection()).andReturn().getResponse().getHeader(headerLocation));
    }

    private List<ReceiptDTO> getReceiptForTest() {
        return List.of(new ReceiptDTO(1L, "testReceipt", new byte[0], "testReceipt", 111L,
                new Author(1L, "testAuthor", "testAuthor", new Country(1L, "testCountry")),
                new Country(1L, "testCountry"), List.of(new Ingredient(1L, "testIngredient",
                BigDecimal.valueOf(111), BigDecimal.valueOf(111), new Receipt(1L))),
                List.of(new StepInfo(1L, "testStepInfo", new byte[0], new Receipt(1L)))));
    }

    private ReceiptDTO getReceiptDTOsForTest() {
        return new ReceiptDTO(1L, "testReceipt", new byte[0], "testReceipt", 111L,
                new Author(1L, "testAuthor", "testAuthor", new Country(1L, "testCountry")),
                new Country(1L, "testCountry"), List.of(new Ingredient(1L, "testIngredient",
                BigDecimal.valueOf(111), BigDecimal.valueOf(111), new Receipt(1L))),
                List.of(new StepInfo(1L, "testStepInfo", new byte[0], new Receipt(1L))));
    }
}
