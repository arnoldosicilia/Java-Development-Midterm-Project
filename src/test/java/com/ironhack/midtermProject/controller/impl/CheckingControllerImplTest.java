package com.ironhack.midtermProject.controller.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ironhack.midtermProject.classes.Address;
import com.ironhack.midtermProject.classes.Money;
import com.ironhack.midtermProject.controller.dto.create.CreateChecking;
import com.ironhack.midtermProject.enums.AccountStatus;
import com.ironhack.midtermProject.model.AccountHolder;
import com.ironhack.midtermProject.model.Checking;
import com.ironhack.midtermProject.repository.AccountHolderRepository;
import com.ironhack.midtermProject.repository.CheckingRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class CheckingControllerImplTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    CheckingRepository checkingRepository;
    @Autowired
    AccountHolderRepository accountHolderRepository;

    private AccountHolder accountHolder1;
    private CreateChecking createChecking;
    private Checking checking1;



    @BeforeEach
    void setUp(){
        mockMvc =  MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();
        accountHolder1 = new AccountHolder("Laura", "laura", "laura", LocalDate.of(1994,2,3), new Address("Calle de la Marina",13));
        accountHolderRepository.save(accountHolder1);
        createChecking = new CreateChecking(accountHolder1.getId(),accountHolder1.getId(),new BigDecimal("1000"),"AAA111", AccountStatus.ACTIVE);
        checking1 = new Checking(accountHolder1, new Money(new BigDecimal("1000")) , "111AAA", AccountStatus.ACTIVE);

        checkingRepository.save(checking1);

    }

    @AfterEach
    void tearDown() {
        checkingRepository.deleteAll();
        accountHolderRepository.deleteAll();
    }


    @Test
    void create() throws Exception {
        mockMvc.perform(post("/admin/checking")
                .with(user("admin").roles("ADMIN"))
                .content(objectMapper.writeValueAsString(createChecking))
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated());
    }

    @Test
    void findAll() throws Exception {
        mockMvc.perform(get("/admin/checking").with(user("admin").roles("ADMIN"))).andExpect(status().isOk());
    }

    @Test
    void findById() throws Exception {
        MvcResult result = mockMvc.perform(get("/admin/checking/"+checking1.getId()).with(user("admin").roles("ADMIN"))).andExpect(status().isOk()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("111AAA"));
    }

    @Test
    void checkBalance() throws Exception {
       // mockMvc.perform(get("/checking/" + checking1.getId() + "/check-balance").with(user("admin").roles("ADMIN"))).andExpect(status().isOk());
    }
    @Test
    void debitBalance() throws Exception {
        //mockMvc.perform(patch("/admin/checking/"+checking1.getId()+"/debit-balance/100").with(user("admin").roles("ADMIN"))).andExpect(status().isNoContent());
    }

    @Test
    void creditBalance() throws Exception {
        mockMvc.perform(patch("/admin/checking/"+checking1.getId()+"/credit-balance/100").with(user("admin").roles("ADMIN"))).andExpect(status().isNoContent());
    }
}