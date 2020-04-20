package com.raines.raineslearn.interesting.getSendBody;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest
public class DemoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetRequest() {
//        this.mockMvc.perform(get("/?id=100").content("Hello, Get Body"))
//                .andDo(print())
//                .andExpect(content().string(is("100: Hello, Get Body")));
    }

}