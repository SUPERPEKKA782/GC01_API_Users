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
        return null;
    }


}
