package com.tomasbozzo.spring.springbootpreview.service;

import com.tomasbozzo.spring.springbootpreview.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {

    @MockBean
    private UserRepository repository;

    @Autowired
    private UserService service;

    @WithMockUser(roles = "ADMIN")
    @Test
    public void testDelete() {
        // When
        service.delete(10L);

        // Then
        verify(repository).deleteById(10L);
    }
}