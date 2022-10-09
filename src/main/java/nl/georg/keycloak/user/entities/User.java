package nl.georg.keycloak.user.entities;

import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table(name = "users", schema = "keycloakdb")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "An user working for the organisation")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @JsonView({
            JsonViews.List.class,
            JsonViews.View.class,
            JsonViews.ViewAll.class,
            JsonViews.Add.class,
            JsonViews.Update.class
    })
    @Schema(description = "The unique ID of the user", example = "123")
    private Long id;

    @JsonView({
            JsonViews.List.class,
            JsonViews.View.class,
            JsonViews.ViewAll.class
    })
    @Schema(description = "The generated token of the user", example = "John")
    @Column(name = "generated_token", unique = true, length = 36)
    private String generatedToken;

    @NotNull
    @JsonView({
            JsonViews.List.class,
            JsonViews.View.class,
            JsonViews.ViewAll.class,
            JsonViews.Add.class,
            JsonViews.Update.class
    })
    @Schema(description = "The first name of the user", example = "John")
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @JsonView({
            JsonViews.List.class,
            JsonViews.View.class,
            JsonViews.ViewAll.class,
            JsonViews.Add.class,
            JsonViews.Update.class
    })
    @Schema(description = "The last name of the user", example = "Smith")
    @Column(name = "last_name")
    private String lastName;

    @JsonView({
            JsonViews.View.class,
            JsonViews.ViewAll.class,
            JsonViews.Add.class,
            JsonViews.Update.class
    })
    @Schema(description = "The phone number of the user", example = "+31 68 123 1234")
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @Email
    @JsonView({
            JsonViews.View.class,
            JsonViews.ViewAll.class,
            JsonViews.Add.class,
            JsonViews.Update.class
    })
    @Schema(description = "The email address of the user", example = "john.smith@example.com")
    private String email;

    @JsonView({
            JsonViews.View.class,
            JsonViews.ViewAll.class,
    })
    @Schema(description = "When the user was created at", example = "2022-10-08")
    @Column(name = "created_at")
    private Instant createdAt;

    @JsonView({
            JsonViews.View.class,
            JsonViews.ViewAll.class,
    })
    @Schema(description = "Whom created the user", example = "John Doe")
    @Column(name = "created_by")
    private String createdBy;

    @JsonView({
            JsonViews.ViewAll.class,
            JsonViews.Add.class,
            JsonViews.Update.class
    })
    @Schema(description = "A note about the user", example = "Just an example user")
    private String note;

    public interface JsonViews {

        interface List {
        }

        interface View {
        }

        interface ViewAll {
        }

        interface Add {
        }

        interface Update {
        }
    }
}