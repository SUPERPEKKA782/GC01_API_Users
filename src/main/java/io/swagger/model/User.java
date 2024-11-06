package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.Profile;
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
 * User
 */
@Validated
@NotUndefined
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-10-31T11:39:55.913399844Z[GMT]")


public class User   {
  @JsonProperty("id")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private Integer id = null;

  @JsonProperty("username")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private String username = null;

  @JsonProperty("email")

  @JsonInclude(JsonInclude.Include.NON_ABSENT)  // Exclude from JSON if absent
  @JsonSetter(nulls = Nulls.FAIL)    // FAIL setting if the value is null
  private String email = null;

  @JsonProperty("profiles")
  @Valid
  private List<Profile> profiles = null;

  public User id(Integer id) { 

    this.id = id;
    return this;
  }

  /**
   * Unique identifier for the user
   * @return id
   **/
  
  @Schema(description = "Unique identifier for the user")
  
  public Integer getId() {  
    return id;
  }



  public void setId(Integer id) { 
    this.id = id;
  }

  public User username(String username) { 

    this.username = username;
    return this;
  }

  /**
   * Username of the user
   * @return username
   **/
  
  @Schema(description = "Username of the user")
  
  public String getUsername() {  
    return username;
  }



  public void setUsername(String username) { 
    this.username = username;
  }

  public User email(String email) { 

    this.email = email;
    return this;
  }

  /**
   * Email of the user
   * @return email
   **/
  
  @Schema(description = "Email of the user")
  
  public String getEmail() {  
    return email;
  }



  public void setEmail(String email) { 
    this.email = email;
  }

  public User profiles(List<Profile> profiles) { 

    this.profiles = profiles;
    return this;
  }

  public User addProfilesItem(Profile profilesItem) {
    if (this.profiles == null) {
      this.profiles = new ArrayList<Profile>();
    }
    this.profiles.add(profilesItem);
    return this;
  }

  /**
   * List of profiles associated with the user
   * @return profiles
   **/
  
  @Schema(description = "List of profiles associated with the user")
  @Valid
  public List<Profile> getProfiles() {  
    return profiles;
  }



  public void setProfiles(List<Profile> profiles) { 
    this.profiles = profiles;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.id, user.id) &&
        Objects.equals(this.username, user.username) &&
        Objects.equals(this.email, user.email) &&
        Objects.equals(this.profiles, user.profiles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, email, profiles);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    username: ").append(toIndentedString(username)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    profiles: ").append(toIndentedString(profiles)).append("\n");
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
