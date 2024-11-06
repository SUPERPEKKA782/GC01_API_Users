package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.openapitools.jackson.nullable.JsonNullable;
import io.swagger.configuration.NotUndefined;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Profile
 */
@Validated
@NotUndefined
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-10-31T11:39:55.913399844Z[GMT]")


public class Profile   {
  @JsonProperty("id")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private Integer id = null;

  @JsonProperty("name")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private String name = null;

  @JsonProperty("userId")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private Integer userId = null;

  @JsonProperty("favorites")
  @Valid
  private List<Integer> favorites = null;
  @JsonProperty("watchLater")
  @Valid
  private List<Integer> watchLater = null;
  @JsonProperty("recentlyWatched")
  @Valid
  private List<Integer> recentlyWatched = null;

  public Profile id(Integer id) { 

    this.id = id;
    return this;
  }

  /**
   * Unique identifier for the profile
   * @return id
   **/
  
  @Schema(description = "Unique identifier for the profile")
  
  public Integer getId() {  
    return id;
  }



  public void setId(Integer id) { 
    this.id = id;
  }

  public Profile name(String name) { 

    this.name = name;
    return this;
  }

  /**
   * Name of the profile
   * @return name
   **/
  
  @Schema(description = "Name of the profile")
  
  public String getName() {  
    return name;
  }



  public void setName(String name) { 
    this.name = name;
  }

  public Profile userId(Integer userId) { 

    this.userId = userId;
    return this;
  }

  /**
   * ID of the associated user
   * @return userId
   **/
  
  @Schema(description = "ID of the associated user")
  
  public Integer getUserId() {  
    return userId;
  }



  public void setUserId(Integer userId) { 
    this.userId = userId;
  }

  public Profile favorites(List<Integer> favorites) { 

    this.favorites = favorites;
    return this;
  }

  public Profile addFavoritesItem(Integer favoritesItem) {
    if (this.favorites == null) {
      this.favorites = new ArrayList<Integer>();
    }
    this.favorites.add(favoritesItem);
    return this;
  }

  /**
   * List of favorite content IDs
   * @return favorites
   **/
  
  @Schema(description = "List of favorite content IDs")
  
  public List<Integer> getFavorites() {  
    return favorites;
  }



  public void setFavorites(List<Integer> favorites) { 
    this.favorites = favorites;
  }

  public Profile watchLater(List<Integer> watchLater) { 

    this.watchLater = watchLater;
    return this;
  }

  public Profile addWatchLaterItem(Integer watchLaterItem) {
    if (this.watchLater == null) {
      this.watchLater = new ArrayList<Integer>();
    }
    this.watchLater.add(watchLaterItem);
    return this;
  }

  /**
   * List of watch later content IDs
   * @return watchLater
   **/
  
  @Schema(description = "List of watch later content IDs")
  
  public List<Integer> getWatchLater() {  
    return watchLater;
  }



  public void setWatchLater(List<Integer> watchLater) { 
    this.watchLater = watchLater;
  }

  public Profile recentlyWatched(List<Integer> recentlyWatched) { 

    this.recentlyWatched = recentlyWatched;
    return this;
  }

  public Profile addRecentlyWatchedItem(Integer recentlyWatchedItem) {
    if (this.recentlyWatched == null) {
      this.recentlyWatched = new ArrayList<Integer>();
    }
    this.recentlyWatched.add(recentlyWatchedItem);
    return this;
  }

  /**
   * List of recently watched content IDs
   * @return recentlyWatched
   **/
  
  @Schema(description = "List of recently watched content IDs")
  
  public List<Integer> getRecentlyWatched() {  
    return recentlyWatched;
  }



  public void setRecentlyWatched(List<Integer> recentlyWatched) { 
    this.recentlyWatched = recentlyWatched;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Profile profile = (Profile) o;
    return Objects.equals(this.id, profile.id) &&
        Objects.equals(this.name, profile.name) &&
        Objects.equals(this.userId, profile.userId) &&
        Objects.equals(this.favorites, profile.favorites) &&
        Objects.equals(this.watchLater, profile.watchLater) &&
        Objects.equals(this.recentlyWatched, profile.recentlyWatched);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, userId, favorites, watchLater, recentlyWatched);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Profile {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    favorites: ").append(toIndentedString(favorites)).append("\n");
    sb.append("    watchLater: ").append(toIndentedString(watchLater)).append("\n");
    sb.append("    recentlyWatched: ").append(toIndentedString(recentlyWatched)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
