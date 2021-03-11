package com.cryptoloan.service;

import com.cryptoloan.domain.User;
import com.cryptoloan.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserDbServiceTest {

    @InjectMocks
    private UserDbService userDbService;

    @Mock
    private UserRepository userRepository;

    @Test
    void shouldReturnEmptyList() {
        // Given
        List<User> userList = new ArrayList<>();
        Mockito.when(userRepository.findAll()).thenReturn(userList);

        // When
        List<User> result = userDbService.getAll();

        // Then
        assertNotNull(result);
        assertEquals(result.size(),0);
    }

    @Test
    void shouldReturnOptional() {
        // Given
        Optional<User> user = Optional.empty();
        Mockito.when(userRepository.findById(1L)).thenReturn(user);

        // When
        Optional<User> result = userDbService.get(1L);

        // Then
        assertNotNull(result);
        assertFalse(result.isPresent());
    }

    @Test
    void shouldReturnSaved() {
        // Given
        User user = new User();
        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);

        // When
        User result = userDbService.save(user);

        // Then
        assertNotNull(result);
        assertEquals(result,user);
    }

}
