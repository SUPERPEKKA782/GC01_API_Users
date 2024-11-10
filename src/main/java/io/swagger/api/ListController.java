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
}
