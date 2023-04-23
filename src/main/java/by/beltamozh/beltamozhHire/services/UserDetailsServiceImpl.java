package by.beltamozh.beltamozhHire.services;

import by.beltamozh.beltamozhHire.models.User;
import by.beltamozh.beltamozhHire.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.getUserByLogin(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("");
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (var role : user.get().getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.get().getLogin(), user.get().getPassword(), grantedAuthorities);
    }
}
