package io.swagger.api;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import io.swagger.model.Profile;
import io.swagger.service.ProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class ListControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfileService profileService;

    private Profile mockProfile;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockProfile = new Profile();
        mockProfile.setId(1);
        mockProfile.setName("John's Profile");
        mockProfile.setUserId(1);
        mockProfile.setFavorites(new ArrayList<>(Arrays.asList(101, 102)));
        mockProfile.setWatchLater(new ArrayList<>(Arrays.asList(201, 202)));
        mockProfile.setRecentlyWatched(new ArrayList<>(Arrays.asList(301, 302)));
    }

    // ----------------- Favorites Tests -----------------

    @Test
    public void testGetFavorites() throws Exception {
        when(profileService.getProfileById(1)).thenReturn(Optional.of(mockProfile));

        mockMvc.perform(get("/users/1/profiles/1/lists/favorites"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0]").value(101))
                .andExpect(jsonPath("$[1]").value(102));
    }

    @Test
    public void testAddFavorite() throws Exception {
        Integer contentIdToAdd = 103;
        Profile updatedProfile = new Profile();
        updatedProfile.setId(1);
        updatedProfile.setName("John's Profile");
        updatedProfile.setUserId(1);
        updatedProfile.setFavorites(Arrays.asList(101, 102, 103));
        updatedProfile.setWatchLater(Arrays.asList(201, 202));
        updatedProfile.setRecentlyWatched(Arrays.asList(301, 302));

        when(profileService.getProfileById(1)).thenReturn(Optional.of(mockProfile));
        when(profileService.updateProfile(1, mockProfile)).thenReturn(updatedProfile);

        mockMvc.perform(post("/users/1/profiles/1/lists/favorites")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contentIdToAdd)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testRemoveFavorite() throws Exception {
        Profile updatedProfile = new Profile();
        updatedProfile.setId(1);
        updatedProfile.setName("John's Profile");
        updatedProfile.setUserId(1);
        updatedProfile.setFavorites(Collections.singletonList(102));
        updatedProfile.setWatchLater(Arrays.asList(201, 202));
        updatedProfile.setRecentlyWatched(Arrays.asList(301, 302));

        when(profileService.getProfileById(1)).thenReturn(Optional.of(mockProfile));
        when(profileService.updateProfile(1, mockProfile)).thenReturn(updatedProfile);

        mockMvc.perform(delete("/users/1/profiles/1/lists/favorites/101"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testRemoveFavoriteNotFound() throws Exception {

        when(profileService.getProfileById(1)).thenReturn(Optional.of(mockProfile));

        mockMvc.perform(delete("/users/1/profiles/1/lists/favorites/999"))
                .andExpect(status().isNotFound());
    }

    // ----------------- Watch Later Tests -----------------

    @Test
    public void testGetWatchLater() throws Exception {
        when(profileService.getProfileById(1)).thenReturn(Optional.of(mockProfile));

        mockMvc.perform(get("/users/1/profiles/1/lists/watch-later"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0]").value(201))
                .andExpect(jsonPath("$[1]").value(202));
    }

    @Test
    public void testAddWatchLater() throws Exception {
        Integer contentIdToAdd = 203;
        Profile updatedProfile = new Profile();
        updatedProfile.setId(1);
        updatedProfile.setName("John's Profile");
        updatedProfile.setUserId(1);
        updatedProfile.setFavorites(Arrays.asList(101, 102));
        updatedProfile.setWatchLater(Arrays.asList(201, 202, 203));
        updatedProfile.setRecentlyWatched(Arrays.asList(301, 302));

        when(profileService.getProfileById(1)).thenReturn(Optional.of(mockProfile));
        when(profileService.updateProfile(1, mockProfile)).thenReturn(updatedProfile);

        mockMvc.perform(post("/users/1/profiles/1/lists/watch-later")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(contentIdToAdd)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testRemoveWatchLater() throws Exception {
        Profile updatedProfile = new Profile();
        updatedProfile.setId(1);
        updatedProfile.setName("John's Profile");
        updatedProfile.setUserId(1);
        updatedProfile.setFavorites(Arrays.asList(101, 102));
        updatedProfile.setWatchLater(Collections.singletonList(202));
        updatedProfile.setRecentlyWatched(Arrays.asList(301, 302));

        when(profileService.getProfileById(1)).thenReturn(Optional.of(mockProfile));
        when(profileService.updateProfile(1, mockProfile)).thenReturn(updatedProfile);

        mockMvc.perform(delete("/users/1/profiles/1/lists/watch-later/201"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testRemoveWatchLaterNotFound() throws Exception {
        when(profileService.getProfileById(1)).thenReturn(Optional.of(mockProfile));

        mockMvc.perform(delete("/users/1/profiles/1/lists/watch-later/999"))
                .andExpect(status().isNotFound());
    }

}
