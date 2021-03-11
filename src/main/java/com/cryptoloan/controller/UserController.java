package com.cryptoloan.controller;

import com.cryptoloan.domain.dto.UserDto;
import com.cryptoloan.exception.UserNotFoundException;
import com.cryptoloan.mapper.UserMapper;
import com.cryptoloan.service.UserDbService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserDbService userDbService;

    private final UserMapper userMapper;

    @GetMapping("users/{id}")
    public UserDto getUser(@PathVariable Long id) throws UserNotFoundException {
        return userMapper.mapToUserDto(userDbService.get(id).orElseThrow(UserNotFoundException::new));
    }

    @GetMapping("users")
    public List<UserDto> listUsers() {
        return userMapper.mapToUserDtoList(userDbService.getAll());
    }

    @PostMapping("users")
    public void addUser(@RequestBody UserDto userDto) {
        userDbService.save(userMapper.mapToUser(userDto));
    }

    @PutMapping("users")
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return userMapper.mapToUserDto(userDbService.save(userMapper.mapToUser(userDto)));
    }

    @DeleteMapping("users/{id}")
    public void deleteUser(@PathVariable Long id) throws UserNotFoundException {
        userDbService.delete(userDbService.get(id).orElseThrow(UserNotFoundException::new));
    }

}
