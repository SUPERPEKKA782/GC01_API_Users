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

}
