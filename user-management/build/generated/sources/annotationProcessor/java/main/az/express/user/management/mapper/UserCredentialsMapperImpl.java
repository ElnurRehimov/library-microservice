package az.express.user.management.mapper;

import az.express.authservice.data.dto.UserCredentialsDTO;
import az.express.authservice.data.entity.UserCredentials;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-29T12:51:14+0400",
    comments = "version: 1.5.5.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.5.jar, environment: Java 17.0.9 (Amazon.com Inc.)"
)
@Component
public class UserCredentialsMapperImpl implements UserCredentialsMapper {

    @Override
    public UserCredentials dtoToEntity(UserCredentialsDTO userCredentialsDTO) {
        if ( userCredentialsDTO == null ) {
            return null;
        }

        UserCredentials userCredentials = new UserCredentials();

        return userCredentials;
    }

    @Override
    public UserCredentialsDTO dtoToEntity(UserCredentials userCredentials) {
        if ( userCredentials == null ) {
            return null;
        }

        UserCredentialsDTO userCredentialsDTO = new UserCredentialsDTO();

        return userCredentialsDTO;
    }
}
