package com.newsapp.userprofile.service;

import com.newsapp.userprofile.exception.UserNameIsTaken;
import com.newsapp.userprofile.model.User;
import com.newsapp.userprofile.repository.UserRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser_ValidUser_ReturnsRegisteredUser() {
        // Arrange
        User user = new User();
        user.setUserName("testUser");
        user.setEmail("test@example.com");
        user.setPassword("testPassword");
        when(userRepo.findUserByUserName(user.getUserName())).thenReturn(Optional.empty());
        when(userRepo.save(user)).thenReturn(user);

        // Act
        User registeredUser = userService.registerUser(user);

        // Assert
        assertNotNull(registeredUser);
        assertEquals(user.getUserName(), registeredUser.getUserName());
        assertEquals(user.getEmail(), registeredUser.getEmail());
        assertEquals(user.getPassword(), registeredUser.getPassword());
        verify(userRepo, times(1)).findUserByUserName(user.getUserName());
        verify(userRepo, times(1)).save(user);
    }

    @Test
    void registerUser_ExistingUserName_ThrowsUserNameIsTakenException() {
        // Arrange
        User user = new User();
        user.setUserName("testUser");
        when(userRepo.findUserByUserName(user.getUserName())).thenReturn(Optional.of(user));

        // Act & Assert
        assertThrows(UserNameIsTaken.class, () -> userService.registerUser(user));
        verify(userRepo, times(1)).findUserByUserName(user.getUserName());
        verify(userRepo, never()).save(user);
    }
}