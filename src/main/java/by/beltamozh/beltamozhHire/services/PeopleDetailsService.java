package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.models.User;
import by.beltamozh.beltamozhHire.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/*@Service
public class PeopleDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserRepository userRepository;

    public PeopleDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> person = Optional.of(userRepository.getUserByName(username));
        if(!person.isPresent())
        {
            throw new UsernameNotFoundException("User Not Found");
        }
        return new PersonDetails(person.get());
    }
}*/
