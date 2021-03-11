package com.cryptoloan.controller;

import com.cryptoloan.domain.User;
import com.cryptoloan.domain.dto.UserDto;
import com.cryptoloan.mapper.UserMapper;
import com.cryptoloan.service.UserDbService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

@SpringJUnitConfig
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserDbService userDbService;

    @MockBean
    private UserMapper userMapper;

    @Test
    void shouldReturnUserDtos() throws Exception {
        // Given
        List<User> userList = List.of(
                new User(1L,"Normal","LastName","12345","99999999",new ArrayList<>()),
                new User(2L,"NotNormal","LastName","12345","99999999",new ArrayList<>())
        );

        Mockito.when(userDbService.getAll()).thenReturn(userList);

        List<UserDto> userDtoList = List.of(
                new UserDto(1L,"Normal","LastName","12345","99999999"),
                new UserDto(2L,"NotNormal","LastName","12345","99999999")
        );

        Mockito.when(userMapper.mapToUserDtoList(Mockito.any())).thenReturn(userDtoList);

        // When & THen
        mockMvc
                .perform(MockMvcRequestBuilders
                .get("/v1/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", Matchers.is("Normal")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].firstName", Matchers.is("NotNormal")));
    }

    @Test
    void shouldReturnUserDto() throws Exception {
        // Given
        User user = new User(5L,"Normal","LastName","12345","99999999",new ArrayList<>());

        Mockito.when(userDbService.save(Mockito.any(User.class))).thenReturn(user);

        UserDto userDto = new UserDto(5L,"Normal","LastName","12345","99999999");

        Mockito.when(userMapper.mapToUserDto(Mockito.any(User.class))).thenReturn(userDto);
        Mockito.when(userMapper.mapToUser(Mockito.any(UserDto.class))).thenReturn(user);

        Gson gson = new Gson();
        String content = gson.toJson(user);

        // When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                .put("/v1/users")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(content))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(5)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", Matchers.is("Normal")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName", Matchers.is("LastName")));
    }

}
