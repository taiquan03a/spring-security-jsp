package com.example.spring_security_jsp_user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserServiceTest {
	@Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUserExists() {
        String username = "testUser";

        // Test case: user does not exist
        when(userRepository.findUserByUsername(username)).thenReturn(null);
        assertFalse(userService.userExists(username));

        // Test case: user exists
        User user = new User();
        when(userRepository.findUserByUsername(username)).thenReturn(user);
        assertTrue(userService.userExists(username));
    }

    @Test
    void testCreateNewUser() {
        User user = new User();
        user.setPassword("plainPassword");

        Role role = new Role();
        when(roleRepository.findById(1)).thenReturn(Optional.of(role));
        when(userRepository.save(user)).thenReturn(user);

        User createdUser = userService.createNewUser(user);

        assertNotNull(createdUser);
        assertNotEquals("plainPassword", createdUser.getPassword()); // Password should be encoded
        assertTrue(new BCryptPasswordEncoder().matches("plainPassword", createdUser.getPassword()));
        assertTrue(createdUser.getRoles().contains(role));
    }
}
