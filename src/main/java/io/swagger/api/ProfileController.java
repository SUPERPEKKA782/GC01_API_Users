package io.swagger.api;

import io.swagger.model.Profile;
import io.swagger.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/profiles")
@Tag(name = "profiles", description = "Operations to manage profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @Operation(summary = "Get all profiles of a user", description = "Retrieve a list of all profiles associated with a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of profiles for the user",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profile.class, type = "array")))
    })
    @GetMapping
    public ResponseEntity<List<Profile>> getProfilesByUserId(
            @io.swagger.v3.oas.annotations.Parameter(description = "ID of the user", required = true)
            @PathVariable Integer userId) {
        List<Profile> profiles = profileService.getProfilesByUserId(userId);
        return ResponseEntity.ok(profiles);
    }

    @Operation(summary = "Create a new profile for a user", description = "Create a new profile under a specific user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Profile created successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profile.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content)
    })
    @PostMapping
    public ResponseEntity<Profile> createProfile(
            @io.swagger.v3.oas.annotations.Parameter(description = "ID of the user", required = true)
            @PathVariable Integer userId,
            @RequestBody(description = "Profile data", required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profile.class)))
            @org.springframework.web.bind.annotation.RequestBody Profile profile) {
        profile.setUserId(userId);
        Profile newProfile = profileService.createProfile(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProfile);
    }

    @Operation(summary = "Get profile details by ID", description = "Retrieve details of a specific profile by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile details",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profile.class))),
            @ApiResponse(responseCode = "404", description = "Profile not found", content = @Content)
    })
    @GetMapping("/{profileId}")
    public ResponseEntity<Profile> getProfileById(
            @io.swagger.v3.oas.annotations.Parameter(description = "ID of the user", required = true)
            @PathVariable Integer userId,
            @io.swagger.v3.oas.annotations.Parameter(description = "ID of the profile", required = true)
            @PathVariable Integer profileId) {
        return profileService.getProfileById(profileId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update a profile", description = "Update the details of an existing profile")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Profile updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profile.class))),
            @ApiResponse(responseCode = "404", description = "Profile not found", content = @Content)
    })
    @PutMapping("/{profileId}")
    public ResponseEntity<Profile> updateProfile(
            @io.swagger.v3.oas.annotations.Parameter(description = "ID of the user", required = true)
            @PathVariable Integer userId,
            @io.swagger.v3.oas.annotations.Parameter(description = "ID of the profile", required = true)
            @PathVariable Integer profileId,
            @RequestBody(description = "Updated profile data", required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Profile.class)))
            @org.springframework.web.bind.annotation.RequestBody Profile profileDetails) throws NotFoundException {
        profileDetails.setUserId(userId);
        Profile updatedProfile = profileService.updateProfile(profileId, profileDetails);
        return ResponseEntity.ok(updatedProfile);
    }

    @Operation(summary = "Delete a profile", description = "Delete a profile by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Profile deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "Profile not found", content = @Content)
    })
    @DeleteMapping("/{profileId}")
    public ResponseEntity<Void> deleteProfile(
            @io.swagger.v3.oas.annotations.Parameter(description = "ID of the user", required = true)
            @PathVariable Integer userId,
            @io.swagger.v3.oas.annotations.Parameter(description = "ID of the profile", required = true)
            @PathVariable Integer profileId) {
        profileService.deleteProfile(profileId);
        return ResponseEntity.noContent().build();
    }
}
