package org.example.repo;

import org.example.model.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private List<User> users = List.of(
            new User( "ivan", "ivan@mail.com"),
            new User( "vasya", "vasya@mail.com")
    );

    @BeforeEach
    public void initDb(){
        for (var user: users)
            userRepository.save(user);
    }

    @Test
    @Transactional
    public void testGetAll(){
        Page<User> page = userRepository.findAll(PageRequest.of(0, 2, Sort.by("id")));
        Assertions.assertEquals(users.size(), page.getContent().size());
        Assertions.assertEquals(users.get(0).getName(), page.getContent().get(0).getName());
        Assertions.assertEquals(users.get(0).getEmail(), page.getContent().get(0).getEmail());

    }

    @AfterEach
    @Transactional
    public void cleanDb(){
        var allUsers = userRepository.findAll();
        for (var user: allUsers)
            userRepository.delete(user);
    }
}
