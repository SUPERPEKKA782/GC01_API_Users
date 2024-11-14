package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.model.User;
import io.swagger.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    private List<User> mockUsers;
    private User mockUser;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockUser = new User();
        mockUser.setId(1);
        mockUser.setUsername("testuser");
        mockUser.setEmail("test@example.com");
        mockUser.setContrasena("password123");
        mockUser.setRol(1);

        User user2 = new User();
        user2.setId(2);
        user2.setUsername("johndoe");
        user2.setEmail("johndoe@example.com");
        user2.setContrasena("password456");
        user2.setRol(2);

        mockUsers = Arrays.asList(mockUser, user2);
    }

    @Test
    public void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(mockUsers);

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].username").value("testuser"))
                .andExpect(jsonPath("$[0].email").value("test@example.com"))
                .andExpect(jsonPath("$[0].rol").value(1))
                .andExpect(jsonPath("$[1].username").value("johndoe"));
    }

    @Test
    public void testCreateUser() throws Exception {
        User userToCreate = new User();
        userToCreate.setUsername("newuser");
        userToCreate.setEmail("newuser@example.com");
        userToCreate.setContrasena("newpassword");
        userToCreate.setRol(1);

        User createdUser = new User();
        createdUser.setId(3);
        createdUser.setUsername("newuser");
        createdUser.setEmail("newuser@example.com");
        createdUser.setContrasena("newpassword");
        createdUser.setRol(1);

        when(userService.createUser(userToCreate)).thenReturn(createdUser);

        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userToCreate)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetUserById() throws Exception {
        when(userService.getUserById(1)).thenReturn(Optional.of(mockUser));

        mockMvc.perform(get("/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.username").value("testuser"))
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.rol").value(1));
    }

    @Test
    public void testGetUserByIdNotFound() throws Exception {
        when(userService.getUserById(999)).thenReturn(Optional.empty());

        mockMvc.perform(get("/users/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateUser() throws Exception {
        User updatedDetails = new User();
        updatedDetails.setUsername("updateduser");
        updatedDetails.setEmail("updated@example.com");
        updatedDetails.setContrasena("updatedpassword");
        updatedDetails.setRol(2);

        User updatedUser = new User();
        updatedUser.setId(1);
        updatedUser.setUsername("updateduser");
        updatedUser.setEmail("updated@example.com");
        updatedUser.setContrasena("updatedpassword");
        updatedUser.setRol(2);

        when(userService.updateUser(1, updatedDetails)).thenReturn(updatedUser);

        mockMvc.perform(put("/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDetails)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteUser() throws Exception {
        mockMvc.perform(delete("/users/1"))
                .andExpect(status().isNoContent());
    }
}