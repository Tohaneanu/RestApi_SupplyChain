package com.example.projectfirsttry.security;

import com.example.projectfirsttry.entities.User;
import com.example.projectfirsttry.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JpaUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<User> userOpt = userRepository.findUserByName(name);
        User user= userOpt.orElseThrow(()-> new UsernameNotFoundException("Username not found in the database"));
        return new SecurityUser(user);
    }
}
