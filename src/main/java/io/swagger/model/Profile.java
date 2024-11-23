package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "profiles")
public class Profile {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)  // Solo visible en la respuesta, no en la solicitud
  @Column(name = "id")
  private Integer id;

  @Column(name = "name", nullable = false)
  private String name;

  @JsonProperty(access = JsonProperty.Access.READ_ONLY)  // Solo visible en la respuesta, no en la solicitud
  @Column(name = "user_id", nullable = false)
  private Integer userId;

  @ElementCollection
  @CollectionTable(name = "profile_favorites", joinColumns = @JoinColumn(name = "profile_id"))
  @Column(name = "favorite_content_id")
  private List<Integer> favorites = new ArrayList<>();

  @ElementCollection
  @CollectionTable(name = "profile_watch_later", joinColumns = @JoinColumn(name = "profile_id"))
  @Column(name = "watch_later_content_id")
  private List<Integer> watchLater = new ArrayList<>();

  @ElementCollection
  @CollectionTable(name = "profile_recently_watched", joinColumns = @JoinColumn(name = "profile_id"))
  @Column(name = "recently_watched_content_id")
  private List<Integer> recentlyWatched = new ArrayList<>();

  // Getters y Setters
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public List<Integer> getFavorites() {
    return favorites;
  }

  public void setFavorites(List<Integer> favorites) {
    this.favorites = favorites;
  }

  public List<Integer> getWatchLater() {
    return watchLater;
  }

  public void setWatchLater(List<Integer> watchLater) {
    this.watchLater = watchLater;
  }

  public List<Integer> getRecentlyWatched() {
    return recentlyWatched;
  }

  public void setRecentlyWatched(List<Integer> recentlyWatched) {
    this.recentlyWatched = recentlyWatched;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Profile)) return false;
    Profile profile = (Profile) o;
    return id != null && id.equals(profile.id);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  @Override
  public String toString() {
    return "Profile{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", userId=" + userId +
            '}';
  }
}
