package nl.georg.keycloak.user.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private String generatedToken;
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
    private UserDTO createdBy;
}
