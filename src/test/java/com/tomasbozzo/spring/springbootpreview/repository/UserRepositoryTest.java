package com.tomasbozzo.spring.springbootpreview.repository;

import com.tomasbozzo.spring.springbootpreview.repository.model.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager manager;

    @Autowired
    private UserRepository repository;

    @Test
    public void testFindAll() {
        // Given
        LongStream.range(1, 11)
                .mapToObj(i -> UserEntity.builder()
                        .id(i)
                        .firstName("First name" + i)
                        .lastName("Last name" + i)
                        .email("email" + i + "@test.com")
                        .build())
                .forEach(e -> manager.merge(e));

        // When
        Stream<UserEntity> result = repository.findAll();

        // Then
        assertThat(result.collect(Collectors.toList()))
                .hasSize(10)
                .allSatisfy(u -> {
                    assertThat(u.getFirstName()).startsWith("First name");
                    assertThat(u.getLastName()).startsWith("Last name");
                    assertThat(u.getEmail()).startsWith("email");
                });
    }
}