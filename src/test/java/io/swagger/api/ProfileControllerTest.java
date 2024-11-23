package io.swagger.api;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
public class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfileService profileService;

    private List<Profile> mockProfiles;
    private Profile mockProfile;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        mockProfile = new Profile();
        mockProfile.setId(1);
        mockProfile.setName("John's Profile");
        mockProfile.setUserId(1);
        mockProfile.setFavorites(Arrays.asList(101, 102));
        mockProfile.setWatchLater(Arrays.asList(201, 202));
        mockProfile.setRecentlyWatched(Arrays.asList(301, 302));

        Profile profile2 = new Profile();
        profile2.setId(2);
        profile2.setName("Jane's Profile");
        profile2.setUserId(1);
        profile2.setFavorites(Collections.singletonList(103));
        profile2.setWatchLater(Collections.singletonList(203));
        profile2.setRecentlyWatched(Collections.singletonList(303));

        mockProfiles = Arrays.asList(mockProfile, profile2);
    }

    @Test
    public void testGetAllProfilesByUserId() throws Exception {
        when(profileService.getProfilesByUserId(1)).thenReturn(mockProfiles);

        mockMvc.perform(get("/users/1/profiles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("John's Profile"))
                .andExpect(jsonPath("$[0].userId").value(1))
                .andExpect(jsonPath("$[0].favorites.length()").value(2))
                .andExpect(jsonPath("$[1].name").value("Jane's Profile"));
    }

    @Test
    public void testCreateProfile() throws Exception {
        Profile profileToCreate = new Profile();
        profileToCreate.setName("New Profile");
        profileToCreate.setUserId(1);
        profileToCreate.setFavorites(Collections.singletonList(104));
        profileToCreate.setWatchLater(Collections.singletonList(204));
        profileToCreate.setRecentlyWatched(Collections.singletonList(304));

        Profile createdProfile = new Profile();
        createdProfile.setId(3);
        createdProfile.setName("New Profile");
        createdProfile.setUserId(1);
        createdProfile.setFavorites(Collections.singletonList(104));
        createdProfile.setWatchLater(Collections.singletonList(204));
        createdProfile.setRecentlyWatched(Collections.singletonList(304));

        when(profileService.createProfile(profileToCreate)).thenReturn(createdProfile);

        mockMvc.perform(post("/users/1/profiles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(profileToCreate)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetProfileById() throws Exception {
        when(profileService.getProfileById(1)).thenReturn(Optional.of(mockProfile));

        mockMvc.perform(get("/users/1/profiles/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("John's Profile"))
                .andExpect(jsonPath("$.userId").value(1))
                .andExpect(jsonPath("$.favorites.length()").value(2));
    }

    @Test
    public void testGetProfileByIdNotFound() throws Exception {
        when(profileService.getProfileById(999)).thenReturn(Optional.empty());

        mockMvc.perform(get("/users/1/profiles/999"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testUpdateProfile() throws Exception {
        Profile updatedDetails = new Profile();
        updatedDetails.setName("Updated Profile");
        updatedDetails.setFavorites(Collections.singletonList(105));
        updatedDetails.setWatchLater(Collections.singletonList(205));
        updatedDetails.setRecentlyWatched(Collections.singletonList(305));

        Profile updatedProfile = new Profile();
        updatedProfile.setId(1);
        updatedProfile.setName("Updated Profile");
        updatedProfile.setUserId(1);
        updatedProfile.setFavorites(Collections.singletonList(105));
        updatedProfile.setWatchLater(Collections.singletonList(205));
        updatedProfile.setRecentlyWatched(Collections.singletonList(305));

        when(profileService.updateProfile(1, updatedDetails)).thenReturn(updatedProfile);

        mockMvc.perform(put("/users/1/profiles/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDetails)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteProfile() throws Exception {
        mockMvc.perform(delete("/users/1/profiles/1"))
                .andExpect(status().isNoContent());
    }
}
