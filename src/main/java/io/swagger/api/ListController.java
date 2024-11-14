package io.swagger.api;

import io.swagger.model.Profile;
import io.swagger.service.ProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/{userId}/profiles/{profileId}/lists")
@Tag(name = "lists", description = "Operations to manage content lists (favorites, watch later, recently watched)")
public class ListController {
    private final ProfileService profileService;

    public ListController(ProfileService profileService) {
        this.profileService = profileService;
    }

    // Favorites List Endpoints

    @Operation(summary = "Get favorite content list", description = "Retrieve the list of favorite content IDs for a specific profile.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Favorite content list retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "array", implementation = Integer.class))),
            @ApiResponse(responseCode = "404", description = "Profile not found",
                    content = @Content)
    })
    @GetMapping("/favorites")
    public ResponseEntity<List<Integer>> getFavorites(
            @Parameter(description = "ID of the user", required = true)
            @PathVariable Integer userId,
            @Parameter(description = "ID of the profile", required = true)
            @PathVariable Integer profileId) {
        Optional<Profile> profileOptional = profileService.getProfileById(profileId);

        if (profileOptional.isPresent()) {
            Profile profile = profileOptional.get();
            return ResponseEntity.ok(profile.getFavorites());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Add content to favorites", description = "Add a content ID to the favorites list of a specific profile.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Content added to favorites successfully",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Profile not found",
                    content = @Content)
    })
    @PostMapping("/favorites")
    public ResponseEntity<Void> addFavorite(
            @Parameter(description = "ID of the user", required = true)
            @PathVariable Integer userId,
            @Parameter(description = "ID of the profile", required = true)
            @PathVariable Integer profileId,
            @Parameter(description = "ID of the content to add to favorites", required = true)
            @RequestBody Integer contentId) throws NotFoundException {
        Optional<Profile> profileOptional = profileService.getProfileById(profileId);

        if (profileOptional.isPresent()) {
            Profile profile = profileOptional.get();
            profile.getFavorites().add(contentId);
            profileService.updateProfile(profileId, profile);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Remove content from favorites", description = "Remove a content ID from the favorites list of a specific profile.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Content removed from favorites successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Content or profile not found",
                    content = @Content)
    })
    @DeleteMapping("/favorites/{contentId}")
    public ResponseEntity<Void> removeFavorite(
            @Parameter(description = "ID of the user", required = true)
            @PathVariable Integer userId,
            @Parameter(description = "ID of the profile", required = true)
            @PathVariable Integer profileId,
            @Parameter(description = "ID of the content to remove from favorites", required = true)
            @PathVariable Integer contentId) throws NotFoundException {
        Optional<Profile> profileOptional = profileService.getProfileById(profileId);

        if (profileOptional.isPresent()) {
            Profile profile = profileOptional.get();
            if (profile.getFavorites().remove(contentId)) {
                profileService.updateProfile(profileId, profile);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Content ID not found in favorites
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Profile not found
        }
    }

    // Watch Later List Endpoints

    @Operation(summary = "Get watch later content list", description = "Retrieve the list of content IDs to watch later for a specific profile.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Watch later content list retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "array", implementation = Integer.class))),
            @ApiResponse(responseCode = "404", description = "Profile not found",
                    content = @Content)
    })
    @GetMapping("/watch-later")
    public ResponseEntity<List<Integer>> getWatchLater(
            @Parameter(description = "ID of the user", required = true)
            @PathVariable Integer userId,
            @Parameter(description = "ID of the profile", required = true)
            @PathVariable Integer profileId) {
        Optional<Profile> profileOptional = profileService.getProfileById(profileId);

        if (profileOptional.isPresent()) {
            Profile profile = profileOptional.get();
            return ResponseEntity.ok(profile.getWatchLater());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Add content to watch later", description = "Add a content ID to the watch later list of a specific profile.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Content added to watch later successfully",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Profile not found",
                    content = @Content)
    })
    @PostMapping("/watch-later")
    public ResponseEntity<Void> addWatchLater(
            @Parameter(description = "ID of the user", required = true)
            @PathVariable Integer userId,
            @Parameter(description = "ID of the profile", required = true)
            @PathVariable Integer profileId,
            @Parameter(description = "ID of the content to add to watch later", required = true)
            @RequestBody Integer contentId) throws NotFoundException {
        Optional<Profile> profileOptional = profileService.getProfileById(profileId);

        if (profileOptional.isPresent()) {
            Profile profile = profileOptional.get();
            profile.getWatchLater().add(contentId);
            profileService.updateProfile(profileId, profile);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Remove content from watch later", description = "Remove a content ID from the watch later list of a specific profile.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Content removed from watch later successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Content or profile not found",
                    content = @Content)
    })
    @DeleteMapping("/watch-later/{contentId}")
    public ResponseEntity<Void> removeWatchLater(
            @Parameter(description = "ID of the user", required = true)
            @PathVariable Integer userId,
            @Parameter(description = "ID of the profile", required = true)
            @PathVariable Integer profileId,
            @Parameter(description = "ID of the content to remove from watch later", required = true)
            @PathVariable Integer contentId) throws NotFoundException {
        Optional<Profile> profileOptional = profileService.getProfileById(profileId);

        if (profileOptional.isPresent()) {
            Profile profile = profileOptional.get();
            if (profile.getWatchLater().remove(contentId)) {
                profileService.updateProfile(profileId, profile);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Content ID not found in watch later list
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Profile not found
        }
    }

    // Recently Watched List Endpoints

    @Operation(summary = "Get recently watched content list", description = "Retrieve the list of recently watched content IDs for a specific profile.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recently watched content list retrieved successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(type = "array", implementation = Integer.class))),
            @ApiResponse(responseCode = "404", description = "Profile not found",
                    content = @Content)
    })
    @GetMapping("/recently-watched")
    public ResponseEntity<List<Integer>> getRecentlyWatched(
            @Parameter(description = "ID of the user", required = true)
            @PathVariable Integer userId,
            @Parameter(description = "ID of the profile", required = true)
            @PathVariable Integer profileId) {
        Optional<Profile> profileOptional = profileService.getProfileById(profileId);

        if (profileOptional.isPresent()) {
            Profile profile = profileOptional.get();
            return ResponseEntity.ok(profile.getRecentlyWatched());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @Operation(summary = "Add content to recently watched", description = "Add a content ID to the recently watched list of a specific profile.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Content added to recently watched successfully",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Profile not found",
                    content = @Content)
    })
    @PostMapping("/recently-watched")
    public ResponseEntity<Void> addRecentlyWatched(
            @Parameter(description = "ID of the user", required = true)
            @PathVariable Integer userId,
            @Parameter(description = "ID of the profile", required = true)
            @PathVariable Integer profileId,
            @Parameter(description = "ID of the content to add to recently watched", required = true)
            @RequestBody Integer contentId) throws NotFoundException {
        Optional<Profile> profileOptional = profileService.getProfileById(profileId);

        if (profileOptional.isPresent()) {
            Profile profile = profileOptional.get();
            profile.getRecentlyWatched().add(contentId);
            profileService.updateProfile(profileId, profile);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "Remove content from recently watched", description = "Remove a content ID from the recently watched list of a specific profile.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Content removed from recently watched successfully",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Content or profile not found",
                    content = @Content)
    })
    @DeleteMapping("/recently-watched/{contentId}")
    public ResponseEntity<Void> removeRecentlyWatched(
            @Parameter(description = "ID of the user", required = true)
            @PathVariable Integer userId,
            @Parameter(description = "ID of the profile", required = true)
            @PathVariable Integer profileId,
            @Parameter(description = "ID of the content to remove from recently watched", required = true)
            @PathVariable Integer contentId) throws NotFoundException {
        Optional<Profile> profileOptional = profileService.getProfileById(profileId);

        if (profileOptional.isPresent()) {
            Profile profile = profileOptional.get();
            if (profile.getRecentlyWatched().remove(contentId)) {
                profileService.updateProfile(profileId, profile);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Content ID not found in recently watched list
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Profile not found
        }
    }

}
