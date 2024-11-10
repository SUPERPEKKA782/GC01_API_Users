package io.swagger.service;

import io.swagger.api.NotFoundException;
import io.swagger.model.Profile;
import io.swagger.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    public Optional<Profile> getProfileById(Integer id) {
        return profileRepository.findById(id);
    }

    public List<Profile> getProfilesByUserId(Integer userId) {
        return profileRepository.findByUserId(userId);
    }

    public Profile updateProfile(Integer id, Profile profileDetails) throws NotFoundException {
        Profile profile = profileRepository.findById(id).orElseThrow(() -> new NotFoundException(404, "Profile not found"));
        profile.setName(profileDetails.getName());
        profile.setFavorites(profileDetails.getFavorites());
        profile.setWatchLater(profileDetails.getWatchLater());
        profile.setRecentlyWatched(profileDetails.getRecentlyWatched());
        return profileRepository.save(profile);
    }

    public void deleteProfile(Integer id) {
        profileRepository.deleteById(id);
    }
}
