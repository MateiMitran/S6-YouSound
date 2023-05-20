//package com.backend.userservicetests.services;
//
//
//import com.backend.userservice.entities.User;
//import com.backend.userservice.repositories.UserRepository;
//import com.backend.userservice.services.UserService;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ContextConfiguration(classes = UserService.class)
//public class UserServiceTests {
//
//
//    @Autowired
//    private UserService userService;
//
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @Test
//    public void testGetAllUsers() {
//        List<User> userList = new ArrayList<>();
//        userList.add(new User("John Doe", "johndoe@example.com"));
//        userList.add(new User("Jane Doe", "janedoe@example.com"));
//        Mockito.when(userRepository.findAll()).thenReturn(userList);
//        List<User> result = userService.getAllUsers();
//        assertEquals(2, result.size());
//        assertEquals("John Doe", result.get(0).getUsername());
//        assertEquals("johndoe@example.com", result.get(0).getPassword());
//        assertEquals("Jane Doe", result.get(1).getUsername());
//        assertEquals("janedoe@example.com", result.get(1).getPassword());
//    }
//
//    @Test
//    public void testUpdateUserRole() {
//        User user = new User("John Doe", "1234");
//        user.setType("admin");
//        user.setId("1");
//        Mockito.when(userRepository.save(user)).thenReturn(user);
//        userRepository.save(user);
//        Mockito.when(userRepository.findById("1")).thenReturn(java.util.Optional.of(user));
//        User result = userService.updateUserRole("1", "admin");
//        assertEquals("admin", result.getType());
//    }
//
//    @Test
//    public void testGetUserById() {
//        User user = new User("John Doe", "1234");
//        user.setId("1");
//        Mockito.when(userRepository.findById("1")).thenReturn(java.util.Optional.of(user));
//        User result = userService.getUserById("1");
//        assertEquals("John Doe", result.getUsername());
//
//    }
//
//
//}
