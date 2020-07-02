package com.ironhack.midtermProject.controller.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.midtermProject.classes.Address;
import com.ironhack.midtermProject.controller.dto.create.CreateAccountHolder;
import com.ironhack.midtermProject.model.AccountHolder;
import com.ironhack.midtermProject.repository.AccountHolderRepository;
import com.ironhack.midtermProject.repository.CheckingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
class AccountHolderControllerImplTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    AccountHolderRepository accountHolderRepository;

    private AccountHolder accountHolder1;
    private CreateAccountHolder createAccountHolder;

    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        accountHolder1 = new AccountHolder("Laura", "laura", "laura", LocalDate.of(1994,2,3), new Address("Calle de la Marina",13));
        accountHolderRepository.save(accountHolder1);
        createAccountHolder = new CreateAccountHolder("lolo","lolo", "lolo", LocalDate.of(1994,2,3), "Calle de la Marina",13,"Calle de la Marina",13);
    }

    @AfterEach
    void tearDown() {
        accountHolderRepository.deleteAll();
    }

    @Test
    void create() throws Exception {
        mockMvc.perform(post("/admin/account-holder")
                .with(user("admin").roles("ADMIN"))
                .content(objectMapper.writeValueAsString(createAccountHolder))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent());
    }
}