package com.ironhack.midtermProject.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.midtermProject.classes.Address;
import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.controller.dto.transference.NewTransference;
import com.ironhack.midtermProject.enums.AccountStatus;
import com.ironhack.midtermProject.model.Account;
import com.ironhack.midtermProject.model.AccountHolder;
import com.ironhack.midtermProject.model.Checking;
import com.ironhack.midtermProject.model.Transference;
import com.ironhack.midtermProject.repository.AccountHolderRepository;
import com.ironhack.midtermProject.repository.CheckingRepository;
import com.ironhack.midtermProject.repository.TransferenceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AccountControllerImplTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;
    @Autowired
    TransferenceRepository tranferenceRepository;

    private AccountHolder accountHolder1;
    private AccountHolder accountHolder2;
    private Checking checking1;
    private Checking checking2;
    private NewTransference newTransference;

    @BeforeEach
    void setUp() {
        mockMvc =  MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        accountHolder1 = new AccountHolder("Laura", "laura", "laura", LocalDate.of(1994,2,3), new Address("Calle de la Marina",13));
        accountHolder2 = new AccountHolder("Pedro", "pedro", "pedro", LocalDate.of(1994,2,3), new Address("Calle de la Mar",1));
        accountHolderRepository.save(accountHolder1);
        accountHolderRepository.save(accountHolder2);
        checking1 = new Checking(accountHolder1,new Money(new BigDecimal("1000")),"111AAA", AccountStatus.ACTIVE);
        checking2 = new Checking(accountHolder2,new Money(new BigDecimal("1000")),"111AAA", AccountStatus.ACTIVE);
        checkingRepository.save(checking1);
        checkingRepository.save(checking2);

        newTransference = new NewTransference(checking1.getId(),"pedro",checking2.getId(), new BigDecimal(200));



    }

    @AfterEach
    void tearDown() {
        accountHolderRepository.deleteAll();
        checkingRepository.deleteAll();
        tranferenceRepository.deleteAll();
    }

    @Test
    void transfer() throws Exception {
        mockMvc.perform(post("/account-holder/transference")
                .with(user("laura").roles("ACCOUNT_HOLDER"))
                .content(objectMapper.writeValueAsString(newTransference))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
}