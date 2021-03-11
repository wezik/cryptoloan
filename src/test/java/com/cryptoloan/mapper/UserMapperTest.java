package com.cryptoloan.mapper;

import com.cryptoloan.domain.User;
import com.cryptoloan.domain.dto.UserDto;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserMapperTest {

    private final UserMapper userMapper = new UserMapper();

    @Test
    void testMapToUser() {
        // Given
        UserDto userDto = new UserDto(5L,"name","kowalski","12354","123");

        // When
        User result = userMapper.mapToUser(userDto);

        // Then
        assertNotNull(result);
        assertEquals(result.getFirstName(),userDto.getFirstName());
        assertEquals(result.getLastName(),userDto.getLastName());
        assertEquals(result.getSocialSecurityNumber(),userDto.getSocialSecurityNumber());
        assertEquals(result.getPhoneNumber(),userDto.getPhoneNumber());
        assertEquals(result.getId(),userDto.getId());

    }

    @Test
    void testMapToUserDto() {
        // Given
        User user = new User(5L,"name","kowalski","12354","123",new ArrayList<>());

        // When
        UserDto result = userMapper.mapToUserDto(user);

        // Then
        assertNotNull(result);
        assertEquals(result.getFirstName(),user.getFirstName());
        assertEquals(result.getLastName(),user.getLastName());
        assertEquals(result.getSocialSecurityNumber(),user.getSocialSecurityNumber());
        assertEquals(result.getPhoneNumber(),user.getPhoneNumber());
        assertEquals(result.getId(),user.getId());

    }

    @Test
    void testMapToUserDtoList() {
        // Given
        List<User> users = List.of(
                new User(5L,"name","kowalski","12354","123",new ArrayList<>()),
                new User(8L,"name","andrzejkowski","12354","123",new ArrayList<>()),
                new User(6L,"name","rabarbar","12354","123",new ArrayList<>())
        );

        // When
        List<UserDto> result = userMapper.mapToUserDtoList(users);

        // Then
        assertNotNull(result);
        for (int i=0; i< result.size(); i++) {
            assertEquals(result.get(i).getId(),users.get(i).getId());
            assertEquals(result.get(i).getFirstName(),users.get(i).getFirstName());
            assertEquals(result.get(i).getLastName(),users.get(i).getLastName());
            assertEquals(result.get(i).getSocialSecurityNumber(),users.get(i).getSocialSecurityNumber());
            assertEquals(result.get(i).getPhoneNumber(),users.get(i).getPhoneNumber());
        }
    }

}
