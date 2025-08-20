package org.example.service;

import org.example.model.User;
import org.example.repo.UserRepository;
import org.example.validation.ServerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

public class UserServiceTest {

    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private UserService userService = new UserService(userRepository);

    @Test
    public void testGetUsersByName() {
        String name = "name";
        User user = new User(1, name, "name@mail.com");
        Mockito.when(userRepository.findAllByName(name)).thenReturn(List.of(user));

        var serviceResult = userService.findAllByName(name);
        Assertions.assertEquals(List.of(user), serviceResult);

        Assertions.assertThrows(ServerException.class, () -> userService.findAllByName(null), "name must not be null");
    }
}
