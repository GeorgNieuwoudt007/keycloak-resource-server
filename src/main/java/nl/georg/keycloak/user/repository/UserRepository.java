package nl.georg.keycloak.user.repository;

import nl.georg.keycloak.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByGeneratedToken(String generatedToken);
}