package com.newsapp.authenticationserver.service;

import com.newsapp.authenticationserver.exception.UserNotFoundException;
import com.newsapp.authenticationserver.model.User;
import com.newsapp.authenticationserver.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void validateUserService_ValidUser_ReturnsTrue() {
        // Arrange
        String userName = "testUser";
        String password = "testPassword";
        User user = new User();
        when(userRepository.validateUser(userName, password)).thenReturn(user);

        // Act
        boolean result = userService.validateUserService(userName, password);

        // Assert
        assertTrue(result);
        verify(userRepository, times(1)).validateUser(userName, password);
    }

    @Test
    void validateUserService_InvalidUser_ReturnsFalse() {
        // Arrange
        String userName = "testUser";
        String password = "testPassword";
        when(userRepository.validateUser(userName, password)).thenReturn(null);

        // Act
        boolean result = userService.validateUserService(userName, password);

        // Assert
        assertFalse(result);
        verify(userRepository, times(1)).validateUser(userName, password);
    }
}
