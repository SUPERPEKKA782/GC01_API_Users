package io.swagger.api;

import io.swagger.model.Profile;
import io.swagger.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @GetMapping
    public ResponseEntity<List<Profile>> getProfilesByUserId(@PathVariable Integer userId) {
        List<Profile> profiles = profileService.getProfilesByUserId(userId);
        return ResponseEntity.ok(profiles);
    }

    @PostMapping
    public ResponseEntity<Profile> createProfile(@PathVariable Integer userId, @RequestBody Profile profile) {
        // Asigna el userId directamente desde el PathVariable en lugar de esperarlo en el JSON
        profile.setUserId(userId);
        Profile newProfile = profileService.createProfile(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProfile);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<Profile> getProfileById(@PathVariable Integer userId, @PathVariable Integer profileId) {
        return profileService.getProfileById(profileId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<Profile> updateProfile(@PathVariable Integer userId, @PathVariable Integer profileId, @RequestBody Profile profileDetails) throws NotFoundException {
        // Asigna el userId desde el PathVariable en lugar de esperarlo en el JSON
        profileDetails.setUserId(userId);
        Profile updatedProfile = profileService.updateProfile(profileId, profileDetails);
        return ResponseEntity.ok(updatedProfile);
    }

    @DeleteMapping("/{profileId}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Integer userId, @PathVariable Integer profileId) {
        profileService.deleteProfile(profileId);
        return ResponseEntity.noContent().build();
    }
}
