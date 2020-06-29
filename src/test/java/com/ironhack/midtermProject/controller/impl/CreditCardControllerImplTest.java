package com.ironhack.midtermProject.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.midtermProject.classes.Address;
import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.controller.dto.create.CreateChecking;
import com.ironhack.midtermProject.controller.dto.create.CreateCreditCard;
import com.ironhack.midtermProject.enums.AccountStatus;
import com.ironhack.midtermProject.model.AccountHolder;
import com.ironhack.midtermProject.model.Checking;
import com.ironhack.midtermProject.model.CreditCard;
import com.ironhack.midtermProject.repository.AccountHolderRepository;
import com.ironhack.midtermProject.repository.CheckingRepository;
import com.ironhack.midtermProject.repository.CreditCardRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CreditCardControllerImplTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;

    private AccountHolder accountHolder1;
    private CreateCreditCard createCreditCard;
    private CreditCard creditCard;

    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();

        accountHolder1 = new AccountHolder("Laura", "laura", "laura", LocalDate.of(1994,2,3), new Address("Calle de la Marina",13));
        accountHolderRepository.save(accountHolder1);
        createCreditCard = new CreateCreditCard(accountHolder1.getId(),accountHolder1.getId(),new BigDecimal("1000"),new BigDecimal("2000"),new BigDecimal("0.2"));
        creditCard = new CreditCard(accountHolder1,new Money(new BigDecimal("1000")));

        creditCardRepository.save(creditCard);
    }

    @AfterEach
    void tearDown() {
        creditCardRepository.deleteAll();
        accountHolderRepository.deleteAll();
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/admin/credit-card")
                .with(user("admin").roles("ADMIN"))
                .content(objectMapper.writeValueAsString(createCreditCard))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/admin/credit-card").with(user("admin").roles("ADMIN"))).andExpect(status().isOk());
    }

    @Test
    void findById() {
    }

    @Test
    void checkBalance() {
    }

    @Test
    void debitBalance() {
    }

    @Test
    void creditBalance() {
    }
}