package nl.georg.keycloak.user.controller;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import nl.georg.keycloak.user.entities.User;
import nl.georg.keycloak.user.models.UserDTO;
import nl.georg.keycloak.user.mapper.UserMapper;
import nl.georg.keycloak.user.repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@SecurityRequirement(name = "security_auth")
@Tag(name = "User Controller", description = "API to retrieve user information")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @Operation(
            summary = "Get a list users",
            description = "Get a list of all users. User role USER required to access this method."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "List of users returned."
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized. User not logged in.",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden. User logged in, but does not have the correct role to access this endpoint.",
                    content = @Content
            )
    })
    @GetMapping
    @JsonView(User.JsonViews.List.class)
    @RolesAllowed("USER")
    public List<UserDTO> getAllUsers() {
        var users = userRepository.findAll(Sort.by("firstName", "lastName"));

        return UserMapper.INSTANCE.userDTOs(users);
    }

    @Operation(
            summary = "Get user details by token",
            description = "Get a limited set of user details by user token. User role USER required to access this method."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "user details returned."
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized. User not logged in.",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden. User logged in, but does not have the correct role to access this endpoint.",
                    content = @Content
            )
    })
    @GetMapping(path = "{userToken}")
    @JsonView(User.JsonViews.View.class)
    @RolesAllowed("USER")
    public UserDTO getUser(@PathVariable
                           @Parameter(
                                   description = "The unique ID of the user",
                                   example = "1"
                           )
                           String userToken) {
        var user = userRepository.findUserByGeneratedToken(userToken);

        if (user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The userToken is invalid");

        return UserMapper.INSTANCE.userDTO(user);
    }

    @Operation(
            summary = "Get ALL user details by ID",
            description = "Get all user details by user token. User role ADMIN required to access this method."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "user details returned."
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized. User not logged in.",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden. User logged in, but does not have the correct role to access this endpoint.",
                    content = @Content
            )
    })
    @GetMapping(path = "{userToken}/details")
    @JsonView(User.JsonViews.ViewAll.class)
    @RolesAllowed("ADMIN")
    public UserDTO getUserAllDetails(@PathVariable
                                     @Parameter(
                                             description = "The unique token of the user",
                                             example = "1"
                                     )
                                     String userToken) {
        return getUser(userToken);
    }
}
