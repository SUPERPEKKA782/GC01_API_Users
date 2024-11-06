package io.swagger.api;

import io.swagger.model.Profile;
import io.swagger.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-10-31T11:39:55.913399844Z[GMT]")
@RestController
public class UsersApiController implements UsersApi {

    private static final Logger log = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<User>> usersGet() {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<User>>(objectMapper.readValue("[ {\n  \"profiles\" : [ {\n    \"favorites\" : [ 5, 5 ],\n    \"recentlyWatched\" : [ 2, 2 ],\n    \"name\" : \"name\",\n    \"watchLater\" : [ 5, 5 ],\n    \"id\" : 6,\n    \"userId\" : 1\n  }, {\n    \"favorites\" : [ 5, 5 ],\n    \"recentlyWatched\" : [ 2, 2 ],\n    \"name\" : \"name\",\n    \"watchLater\" : [ 5, 5 ],\n    \"id\" : 6,\n    \"userId\" : 1\n  } ],\n  \"id\" : 0,\n  \"email\" : \"email\",\n  \"username\" : \"username\"\n}, {\n  \"profiles\" : [ {\n    \"favorites\" : [ 5, 5 ],\n    \"recentlyWatched\" : [ 2, 2 ],\n    \"name\" : \"name\",\n    \"watchLater\" : [ 5, 5 ],\n    \"id\" : 6,\n    \"userId\" : 1\n  }, {\n    \"favorites\" : [ 5, 5 ],\n    \"recentlyWatched\" : [ 2, 2 ],\n    \"name\" : \"name\",\n    \"watchLater\" : [ 5, 5 ],\n    \"id\" : 6,\n    \"userId\" : 1\n  } ],\n  \"id\" : 0,\n  \"email\" : \"email\",\n  \"username\" : \"username\"\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<User>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> usersPost(@Parameter(in = ParameterIn.DEFAULT, description = "User data", required=true, schema=@Schema()) @Valid @RequestBody User body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> usersUserIdDelete(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("userId") Integer userId
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<User> usersUserIdGet(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("userId") Integer userId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<User>(objectMapper.readValue("{\n  \"profiles\" : [ {\n    \"favorites\" : [ 5, 5 ],\n    \"recentlyWatched\" : [ 2, 2 ],\n    \"name\" : \"name\",\n    \"watchLater\" : [ 5, 5 ],\n    \"id\" : 6,\n    \"userId\" : 1\n  }, {\n    \"favorites\" : [ 5, 5 ],\n    \"recentlyWatched\" : [ 2, 2 ],\n    \"name\" : \"name\",\n    \"watchLater\" : [ 5, 5 ],\n    \"id\" : 6,\n    \"userId\" : 1\n  } ],\n  \"id\" : 0,\n  \"email\" : \"email\",\n  \"username\" : \"username\"\n}", User.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<User>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Profile>> usersUserIdProfilesGet(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("userId") Integer userId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Profile>>(objectMapper.readValue("[ {\n  \"favorites\" : [ 5, 5 ],\n  \"recentlyWatched\" : [ 2, 2 ],\n  \"name\" : \"name\",\n  \"watchLater\" : [ 5, 5 ],\n  \"id\" : 6,\n  \"userId\" : 1\n}, {\n  \"favorites\" : [ 5, 5 ],\n  \"recentlyWatched\" : [ 2, 2 ],\n  \"name\" : \"name\",\n  \"watchLater\" : [ 5, 5 ],\n  \"id\" : 6,\n  \"userId\" : 1\n} ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Profile>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Profile>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> usersUserIdProfilesPost(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("userId") Integer userId
,@Parameter(in = ParameterIn.DEFAULT, description = "Profile data", required=true, schema=@Schema()) @Valid @RequestBody Profile body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> usersUserIdProfilesProfileIdDelete(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("userId") Integer userId
,@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("profileId") Integer profileId
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Profile> usersUserIdProfilesProfileIdGet(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("userId") Integer userId
,@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("profileId") Integer profileId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<Profile>(objectMapper.readValue("{\n  \"favorites\" : [ 5, 5 ],\n  \"recentlyWatched\" : [ 2, 2 ],\n  \"name\" : \"name\",\n  \"watchLater\" : [ 5, 5 ],\n  \"id\" : 6,\n  \"userId\" : 1\n}", Profile.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Profile>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Profile>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> usersUserIdProfilesProfileIdListsFavoritesContentIdDelete(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("userId") Integer userId
,@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("profileId") Integer profileId
,@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("contentId") Integer contentId
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Integer>> usersUserIdProfilesProfileIdListsFavoritesGet(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("userId") Integer userId
,@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("profileId") Integer profileId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Integer>>(objectMapper.readValue("[ 0, 0 ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Integer>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Integer>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> usersUserIdProfilesProfileIdListsFavoritesPost(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("userId") Integer userId
,@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("profileId") Integer profileId
,@Parameter(in = ParameterIn.DEFAULT, description = "ID of the content to be added to favorites", required=true, schema=@Schema()) @Valid @RequestBody Integer body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> usersUserIdProfilesProfileIdListsRecentlyWatchedContentIdDelete(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("userId") Integer userId
,@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("profileId") Integer profileId
,@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("contentId") Integer contentId
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Integer>> usersUserIdProfilesProfileIdListsRecentlyWatchedGet(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("userId") Integer userId
,@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("profileId") Integer profileId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Integer>>(objectMapper.readValue("[ 0, 0 ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Integer>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Integer>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> usersUserIdProfilesProfileIdListsRecentlyWatchedPost(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("userId") Integer userId
,@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("profileId") Integer profileId
,@Parameter(in = ParameterIn.DEFAULT, description = "ID of the content to be added to recently watched list", required=true, schema=@Schema()) @Valid @RequestBody Integer body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> usersUserIdProfilesProfileIdListsWatchLaterContentIdDelete(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("userId") Integer userId
,@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("profileId") Integer profileId
,@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("contentId") Integer contentId
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<List<Integer>> usersUserIdProfilesProfileIdListsWatchLaterGet(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("userId") Integer userId
,@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("profileId") Integer profileId
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<Integer>>(objectMapper.readValue("[ 0, 0 ]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<Integer>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<Integer>>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> usersUserIdProfilesProfileIdListsWatchLaterPost(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("userId") Integer userId
,@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("profileId") Integer profileId
,@Parameter(in = ParameterIn.DEFAULT, description = "ID of the content to be added to watch later list", required=true, schema=@Schema()) @Valid @RequestBody Integer body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> usersUserIdProfilesProfileIdPut(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("userId") Integer userId
,@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("profileId") Integer profileId
,@Parameter(in = ParameterIn.DEFAULT, description = "Updated profile data", required=true, schema=@Schema()) @Valid @RequestBody Profile body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> usersUserIdPut(@Parameter(in = ParameterIn.PATH, description = "", required=true, schema=@Schema()) @PathVariable("userId") Integer userId
,@Parameter(in = ParameterIn.DEFAULT, description = "Updated user data", required=true, schema=@Schema()) @Valid @RequestBody User body
) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
