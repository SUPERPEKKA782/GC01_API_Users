package io.swagger.repository;

import io.swagger.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    List<Profile> findByUserId(Integer userId);  // Método para obtener perfiles por `userId`
}