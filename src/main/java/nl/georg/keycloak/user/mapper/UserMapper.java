package nl.georg.keycloak.user.mapper;

import nl.georg.keycloak.user.entities.User;
import nl.georg.keycloak.user.models.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO userDTO(User user);

    User user(UserDTO userDTO);

    List<UserDTO> userDTOs(List<User> users);

    List<User> users(List<UserDTO> users);
}
