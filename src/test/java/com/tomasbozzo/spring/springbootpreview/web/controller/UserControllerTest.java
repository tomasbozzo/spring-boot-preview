package com.tomasbozzo.spring.springbootpreview.web.controller;

import com.tomasbozzo.spring.springbootpreview.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testCreateUser() throws Exception {
        // Given
        String content = "{\"id\":2,\"firstName\":\"Tomás\",\"lastName\":\"Bozzo\",\"email\":\"tomasbozzo@fakemail.com\"}";
        mockMvc.perform(post("/users").content(content).contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isCreated())
                .andReturn();
    }
}