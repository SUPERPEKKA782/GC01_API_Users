package io.swagger.api;

import io.swagger.model.Profile;
import io.swagger.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users/{userId}/profiles/{profileId}/lists")
public class ListController {
    private final ProfileService profileService;

    public ListController(ProfileService profileService) {
        this.profileService = profileService;
    }

    // Favorites List Endpoints
    @GetMapping("/favorites")
    public ResponseEntity<List<Integer>> getFavorites(@PathVariable Integer userId, @PathVariable Integer profileId) {
        Optional<Profile> profileOptional = profileService.getProfileById(profileId);

        if (profileOptional.isPresent()) {
            Profile profile = profileOptional.get();
            return ResponseEntity.ok(profile.getFavorites());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/favorites")
    public ResponseEntity<Void> addFavorite(@PathVariable Integer userId, @PathVariable Integer profileId, @RequestBody Integer contentId) throws NotFoundException {
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

    @DeleteMapping("/favorites/{contentId}")
    public ResponseEntity<Void> removeFavorite(@PathVariable Integer userId, @PathVariable Integer profileId, @PathVariable Integer contentId) throws NotFoundException {
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
    @GetMapping("/watch-later")
    public ResponseEntity<List<Integer>> getWatchLater(@PathVariable Integer userId, @PathVariable Integer profileId) {
        Optional<Profile> profileOptional = profileService.getProfileById(profileId);

        if (profileOptional.isPresent()) {
            Profile profile = profileOptional.get();
            return ResponseEntity.ok(profile.getWatchLater());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/watch-later")
    public ResponseEntity<Void> addWatchLater(@PathVariable Integer userId, @PathVariable Integer profileId, @RequestBody Integer contentId) throws NotFoundException {
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

    @DeleteMapping("/watch-later/{contentId}")
    public ResponseEntity<Void> removeWatchLater(@PathVariable Integer userId, @PathVariable Integer profileId, @PathVariable Integer contentId) throws NotFoundException {
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

}
