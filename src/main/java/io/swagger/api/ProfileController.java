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

    @PostMapping
    public ResponseEntity<Profile> createProfile(@PathVariable Integer userId, @RequestBody Profile profile) {
        // Asigna el userId directamente desde el PathVariable en lugar de esperarlo en el JSON
        profile.setUserId(userId);
        Profile newProfile = profileService.createProfile(profile);
        return ResponseEntity.status(HttpStatus.CREATED).body(newProfile);
    }
}
