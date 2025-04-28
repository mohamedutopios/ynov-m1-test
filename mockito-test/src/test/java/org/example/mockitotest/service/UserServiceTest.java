package org.example.mockitotest.service;

import org.example.mockitotest.exception.EntityNotFoundException;
import org.example.mockitotest.exception.InvalidEmailException;
import org.example.mockitotest.model.User;
import org.example.mockitotest.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;


public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllUsers() {
        //given
        User user = new User(1L, "Flavian", "bogoss_maubeuge@gmail.com");
        User user1 = new User(2L, "Maneo", "other_bogoss_maubeuge@gmail.com");
        List<User> users = Arrays.asList(user, user1);
        when(userRepository.findAll()).thenReturn(users);

        // when
        List<User> users1 = userService.getAllUsers();

        // then
        assertEquals(2, users1.size());
        verify(userRepository, times(1)).findAll();

    }


    @Test
    public void testGetUserById(){
        // Given
        User user = new User(1L, "Mathieu","mathieu@gmail.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // when
        Optional<User> user1 = userService.getUserById(1L);

        // Then
        assertTrue(user1.isPresent());
        assertEquals("Mathieu", user1.get().getName());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    public void testSaveUser(){
        // Given
        User user = new User(1L, "Mathieu","mathieu@gmail.com");
        when(userRepository.save(any(User.class))).thenReturn(user);

        // When
        User user1 = userService.saveUser(user);

        // Then
        assertEquals("Mathieu",user1.getName());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testUpdateUser(){
        // Given
        User existingUser = new User(1L, "Michel","michel@gmail.com");
        User updateUser = new User(1L, "Michel","michelo@gmail.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));

        Optional<User> user1 = userService.getUserById(1L);
        assertTrue(user1.isPresent());

        when(userRepository.save(any(User.class))).thenReturn(updateUser);

        User userUp = userService.updateUser(1L,updateUser);

        assertEquals("michelo@gmail.com", userUp.getEmail());

        verify(userRepository,times(1)).findById(1L);
        verify(userRepository,times(1)).save(updateUser);

    }

    @Test
    public void testDeleteUser(){

        // Given
        User user = new User(1L, "tom","tom@gmail.com");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // When
        Optional<User> user1 = userService.getUserById(1L);

        // Then
        assertTrue(user1.isPresent());

        // Given
        doNothing().when(userRepository).deleteById(1L);

        // When
        userService.deleteUserById(1L);

        // Then
        verify(userRepository, times(1)).deleteById(1L);

    }


    @Test
    public void testSaveInvalidMail(){
        User user = new User();
        user.setEmail("invalid.mail.com");
        assertThrows(InvalidEmailException.class, ()-> userService.saveUser(user));
        verify(userRepository, never()).save(user);
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
