package com.service.bearrecipes.controller.page;

import com.service.bearrecipes.config.DbTestcontainersConfig;
import com.service.bearrecipes.config.security.SecurityConfiguration;
import com.service.bearrecipes.dto.ReceiptDTO;
import com.service.bearrecipes.model.*;
import com.service.bearrecipes.service.IngredientService;
import com.service.bearrecipes.service.ReceiptService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("junit")
@Import(SecurityConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {DbTestcontainersConfig.class})
public class IngredientPageControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

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
    public void getAddReceiptPageTest() {
        Mockito.when(receiptService.findById(1L)).thenReturn(getReceiptDTOsForTest());

        URI uri = UriComponentsBuilder
                .fromUriString("/ingredient/addingredient/1")
                .build()
                .encode()
                .toUri();

        ResponseEntity<String> rsp = restTemplate.exchange(uri, HttpMethod.GET,
                new HttpEntity<>(null), String.class);

        assertEquals(HttpStatus.OK, rsp.getStatusCode());
        assertNotNull(rsp.getBody());

        assertTrue(rsp.getBody().contains("testReceiptDTO"));
        assertTrue(rsp.getBody().contains("111"));
    }

    @Test
    public void unauthorizedUserRedirectToLoginPageTest() {
        String redirectUrl = "http://localhost/login";
        String headerLocation = "Location";

        ResponseEntity<String> rsp = restTemplate.exchange("/ingredient/addingredient/1", HttpMethod.GET,
                new HttpEntity<>(null), String.class);

        assertEquals(HttpStatus.PERMANENT_REDIRECT, rsp.getStatusCode());
        assertNotNull(rsp.getBody());
    }

    private ReceiptDTO getReceiptDTOsForTest() {
        return new ReceiptDTO(1L, "testReceipt", new byte[0], "testReceipt", 111L,
                new Author(1L, "testAuthor", "testAuthor", new Country(1L, "testCountry")),
                new Country(1L, "testCountry"), List.of(new Ingredient(1L, "testIngredient",
                BigDecimal.valueOf(111), BigDecimal.valueOf(111), new Receipt(1L))),
                List.of(new StepInfo(1L, "testStepInfo", new byte[0], new Receipt(1L))));
    }
}
