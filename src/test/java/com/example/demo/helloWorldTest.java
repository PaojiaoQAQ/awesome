package com.example.demo;

import com.example.demo.controller.ProduceController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProduceController.class)
public class helloWorldTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void getReturnStrTest() throws Exception{
        String result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/getAllUserInfo/{111}")
                        .accept(MediaType.APPLICATION_JSON))
                        .andDo(MockMvcResultHandlers.print())
                        .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }
}
