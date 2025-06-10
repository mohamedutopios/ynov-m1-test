package org.example.userunittestmockitointegration.service;


import org.example.userunittestmockitointegration.exception.EntityNotFoundException;
import org.example.userunittestmockitointegration.exception.InvalidEmailException;
import org.example.userunittestmockitointegration.model.User;
import org.example.userunittestmockitointegration.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsers(){
        User user = new User(1L, "Mathieu","mathieu@gmail.com");
        User user1 = new User(2L, "Jim","jim@gmail.com");
        List<User> listUser = Arrays.asList(user1, user);
        when(userRepository.findAll()).thenReturn(listUser);

        List<User> users = userService.getAllUsers();

        assertEquals(2, users.size());

        verify(userRepository, times(1)).findAll();
    }


    @Test
    public void testGetAllUsersEmptyList() {
        when(userRepository.findAll()).thenReturn(List.of());

        List<User> users = userService.getAllUsers();

        assertTrue(users.isEmpty());
        verify(userRepository).findAll();
    }


    @Test
    public void testGetUserById(){
        User user = new User(1L, "Mathieu","mathieu@gmail.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        Optional<User> user1 = userService.getUserById(1L);

        assertTrue(user1.isPresent());

        assertEquals("Mathieu", user1.get().getName());

        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testSaveUserWithInvalidEmailExceptionMessage() {
        User invalidUser = new User(null, "Tom", "tom.gmail.com");

        InvalidEmailException thrown = assertThrows(InvalidEmailException.class, () -> {
            userService.saveUser(invalidUser);
        });

        assertEquals("Invalid format email", thrown.getMessage());
    }


    @Test
    public void testSaveUser(){
        User user = new User(1L, "Mathieu","mathieu@gmail.com");
        when(userRepository.save(any(User.class))).thenReturn(user);
        User user1 = userService.saveUser(user);
        assertEquals("Mathieu",user1.getName());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testUpdateUser(){
        User existingUser = new User(1L, "Michel","michel@gmail.com");
        User updateUser = new User(1L, "Michel","michelo@gmail.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        Optional<User> user1 = userService.getUserById(1L);
        assertTrue(user1.isPresent());
        when(userRepository.save(any(User.class))).thenReturn(updateUser);
        User userUp = userService.updateUser(1L,updateUser);
        assertEquals("michelo@gmail.com", userUp.getEmail());
        verify(userRepository,times(2)).findById(1L);
        verify(userRepository,times(1)).save(updateUser);

    }

    @Test
    public void testDeleteUser(){
        User user = new User(1L, "tom","tom@gmail.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        Optional<User> user1 = userService.getUserById(1L);
        assertTrue(user1.isPresent());

        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUserById(1L);

        verify(userRepository, times(1)).deleteById(1L);

    }


    @Test
    public void testUpdateWhenUserNotExist(){
        User user = new User();
        user.setEmail("toto@gmail.com");
        when(userRepository.findById(1L)).thenReturn(Optional.empty());


        EntityNotFoundException e = assertThrows(EntityNotFoundException.class, ()-> userService.updateUser(1L,user));
        assertEquals("User not found", e.getMessage());
        verify(userRepository,times(1)).findById(1L);
        verify(userRepository, never()).save(user);

    }



}
