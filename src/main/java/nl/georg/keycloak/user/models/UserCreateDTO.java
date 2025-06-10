package nl.georg.keycloak.user.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDTO {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String phoneNumber;
    @NotNull
    @Email
    private String email;
    private String note;
    private Instant createdAt;
    private Integer parentId;
    private UserDTO createdBy;
}
