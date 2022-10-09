package nl.georg.keycloak.user.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
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
