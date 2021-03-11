package com.cryptoloan.mapper;

import com.cryptoloan.domain.Loan;
import com.cryptoloan.domain.User;
import com.cryptoloan.domain.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    public User mapToUser(UserDto userDto, List<Loan> loans) {
        return new User(userDto.getId(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getSocialSecurityNumber(),
                userDto.getPhoneNumber(),
                loans);
    }

    public User mapToUser(UserDto userDto) {
        return mapToUser(userDto, Collections.emptyList());
    }

    public UserDto mapToUserDto(User user) {
        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getSocialSecurityNumber(),
                user.getPhoneNumber());
    }

    public List<UserDto> mapToUserDtoList(List<User> users) {
        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

}
