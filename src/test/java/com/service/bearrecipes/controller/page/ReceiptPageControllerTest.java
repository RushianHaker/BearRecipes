package com.service.bearrecipes.controller.page;

import com.service.bearrecipes.config.DbTestcontainersConfig;
import com.service.bearrecipes.config.security.SecurityConfiguration;
import com.service.bearrecipes.dto.ReceiptDTO;
import com.service.bearrecipes.model.*;
import com.service.bearrecipes.service.AuthorService;
import com.service.bearrecipes.service.CountryService;
import com.service.bearrecipes.service.ReceiptService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("junit")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {SecurityConfiguration.class, DbTestcontainersConfig.class})
public class ReceiptPageControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

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
    public void getAllReceiptListPageTest() {
        Mockito.when(receiptService.findAll()).thenReturn(getReceiptForTest());

        URI uri = UriComponentsBuilder
                .fromUriString("/receipt")
                .build()
                .encode()
                .toUri();

        ResponseEntity<String> rsp = restTemplate.exchange(uri, HttpMethod.GET,
                new HttpEntity<>(null), String.class);

        assertEquals(HttpStatus.OK, rsp.getStatusCode());
        assertNotNull(rsp.getBody());

        assertTrue(rsp.getBody().contains("<td> <a href=\\\"/receipt/inforeceipt/\" + receipt.id + \"\\\">"));
        assertTrue(rsp.getBody().contains("<button type=\\\"button\\\">Info</button> </td>"));
    }

    @DisplayName("Получение страницы добавления")
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void getAddReceiptPageTest() {
        URI uri = UriComponentsBuilder
                .fromUriString("/receipt/addreceipt")
                .build()
                .encode()
                .toUri();

        ResponseEntity<String> rsp = restTemplate.exchange(uri, HttpMethod.GET,
                new HttpEntity<>(null), String.class);

        assertEquals(HttpStatus.OK, rsp.getStatusCode());
        assertNotNull(rsp.getBody());

        assertTrue(rsp.getBody().contains("<input id=\"country-input\" name=\"country-input\" type=\"text\"/>"));
    }

    @DisplayName("Получение страницы редактирования")
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void getEditReceiptPageTest() {
        Mockito.when(receiptService.findById(TEST_RECEIPT_ID)).thenReturn(getReceiptDTOsForTest());

        URI uri = UriComponentsBuilder
                .fromUriString("/receipt/editreceipt/1")
                .build()
                .encode()
                .toUri();

        ResponseEntity<String> rsp = restTemplate.exchange(uri, HttpMethod.GET,
                new HttpEntity<>(null), String.class);

        assertEquals(HttpStatus.OK, rsp.getStatusCode());
        assertNotNull(rsp.getBody());

        assertTrue(rsp.getBody().contains("testReceiptDTO"));
        assertTrue(rsp.getBody().contains("testAuthorReceiptDTO"));
        assertTrue(rsp.getBody().contains("dinnerReceiptDTO"));
    }

    @DisplayName("Получение страницы полной информации по рецепту")
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void getInfoReceiptPageTest() {
        Mockito.when(receiptService.findById(TEST_RECEIPT_ID)).thenReturn(getReceiptDTOsForTest());

        URI uri = UriComponentsBuilder
                .fromUriString("/receipt/inforeceipt/1")
                .build()
                .encode()
                .toUri();

        ResponseEntity<String> rsp = restTemplate.exchange(uri, HttpMethod.GET,
                new HttpEntity<>(null), String.class);

        assertEquals(HttpStatus.OK, rsp.getStatusCode());
        assertNotNull(rsp.getBody());

        assertTrue(rsp.getBody().contains("testReceiptDTO"));
        assertTrue(rsp.getBody().contains("testAuthorReceiptDTO"));
    }

    @DisplayName("Получение страницы удаления рецептов")
    @WithMockUser(
            username = "admin",
            authorities = {"ROLE_ADMIN"}
    )
    @Test
    public void getDeleteReceiptPageTest() {
        Mockito.when(receiptService.findById(TEST_RECEIPT_ID)).thenReturn(getReceiptDTOsForTest());

        URI uri = UriComponentsBuilder
                .fromUriString("/receipt/delreceipt/1")
                .build()
                .encode()
                .toUri();

        ResponseEntity<String> rsp = restTemplate.exchange(uri, HttpMethod.GET,
                new HttpEntity<>(null), String.class);

        assertEquals(HttpStatus.OK, rsp.getStatusCode());
        assertNotNull(rsp.getBody());

        assertTrue(rsp.getBody().contains("testReceiptDTO"));
        assertTrue(rsp.getBody().contains("testAuthorReceiptDTO"));
    }

    @Test
    public void unauthorizedUserRedirectToLoginPageTest() {
        String redirectUrl = "http://localhost/login";
        String headerLocation = "Location";

        ResponseEntity<String> rsp = restTemplate.exchange("/receipt", HttpMethod.GET,
                new HttpEntity<>(null), String.class);
        assertEquals(HttpStatus.PERMANENT_REDIRECT, rsp.getStatusCode());

        rsp = restTemplate.exchange("/receipt/addreceipt", HttpMethod.GET,
                new HttpEntity<>(null), String.class);
        assertEquals(HttpStatus.PERMANENT_REDIRECT, rsp.getStatusCode());

        rsp = restTemplate.exchange("/receipt/editreceipt/1", HttpMethod.GET,
                new HttpEntity<>(null), String.class);
        assertEquals(HttpStatus.PERMANENT_REDIRECT, rsp.getStatusCode());

        rsp = restTemplate.exchange("/receipt/inforeceipt/1", HttpMethod.GET,
                new HttpEntity<>(null), String.class);
        assertEquals(HttpStatus.PERMANENT_REDIRECT, rsp.getStatusCode());

        rsp = restTemplate.exchange("/receipt/delreceipt/1", HttpMethod.GET,
                new HttpEntity<>(null), String.class);
        assertEquals(HttpStatus.PERMANENT_REDIRECT, rsp.getStatusCode());
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
