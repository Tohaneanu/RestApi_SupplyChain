package com.example.projectfirsttry.services;

import com.example.projectfirsttry.entities.Roles;
import com.example.projectfirsttry.entities.User;
import com.example.projectfirsttry.repository.UserRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    void setUserRepository() {
        userRepository = Mockito.mock(UserRepository.class);
        userService =Mockito.mock(UserService.class);
    }

    @AfterEach
    void tearDown() {
        userRepository = null;
        userService =null;
    }


    @Test
    @DisplayName("method to return user by id, from /user/{id}")
    void shouldReturnUserByID() {
        int id = 1;
        User user = new User();
        user.setName("Admin1");
        user.setId(1);
        user.setPassword("123");
        Roles roles=new Roles();
        roles.setId(1);
        roles.setName("ROLE_ADMIN");
        roles.setUser(user);
        List<Roles> rolesList=List.of(roles);
        user.setRoles(rolesList);
        when(userService.getUserById(id)).thenReturn(user);

        User result=userService.getUserById(id);
        assertNotNull(result);
        assertEquals(user.getName(),result.getName());


    }
}
