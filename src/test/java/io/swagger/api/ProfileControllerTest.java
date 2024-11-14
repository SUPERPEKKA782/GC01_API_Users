package io.swagger.api;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
}
