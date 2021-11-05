package net.k9connect.k9connect.services;

import net.k9connect.k9connect.models.User;
import net.k9connect.k9connect.models.UserWithRoles;
import net.k9connect.k9connect.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetailsLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("No user found for " + username);
        } else if (user.getStatus().toString().equals("banned") || user.getStatus().toString().equals("inactive")) {
            System.out.println(username + " is banned.");
            throw new UsernameNotFoundException(username + " is banned.");
        }

        return new UserWithRoles(user);
    }

}