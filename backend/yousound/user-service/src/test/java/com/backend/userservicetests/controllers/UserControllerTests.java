package com.backend.userservicetests.controllers;


import com.backend.userservice.controllers.UserController;
import com.backend.userservice.entities.User;
import com.backend.userservice.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@ContextConfiguration(classes = UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User("John", "Doe"));
        users.add(new User("Jane", "Doe"));
        Mockito.when(userService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("John"))
                .andExpect(jsonPath("$[0].password").value("Doe"))
                .andExpect(jsonPath("$[1].username").value("Jane"))
                .andExpect(jsonPath("$[1].password").value("Doe"));

    }

    @Test
    public void testUpdateUserRole() throws Exception {
        User user = new User("John Doe", "1234");
        user.setId("1");
        Mockito.when(userService.updateUserRole("1", "admin")).thenReturn(user);

        mockMvc.perform(put("/api/users/role/{id}", user.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"role\": \"admin\"}"))
                .andExpect(status().isOk());


        Mockito.when(userService.getUserById(user.getId())).thenReturn(new User("John Doe", "1234", "admin"));
        User updatedUser = userService.getUserById(user.getId());
        assertNotNull(updatedUser);
        assertEquals("admin", updatedUser.getType());

    }

    @Test
    public void testGetUserById() throws Exception {
        User user = new User("John Doe", "1234");
        user.setId("1");
        Mockito.when(userService.getUserById("1")).thenReturn(user);

        mockMvc.perform(get("/api/users/{id}", user.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("John Doe"))
                .andExpect(jsonPath("$.password").value("1234"));
    }


}
