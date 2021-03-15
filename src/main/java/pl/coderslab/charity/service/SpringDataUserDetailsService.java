package pl.coderslab.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.coderslab.charity.entity.CurrentUser;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.repository.UserRepository;


import java.util.HashSet;
import java.util.Set;

public class SpringDataUserDetailsService implements UserDetailsService {

    private UserRepository userRepo;

    @Autowired
    public void setUserRepository(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public CurrentUser loadUserByUsername(String email) {
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        if (user.getEnabled() == 0) {
            throw new DisabledException("Account not activated. Please check email.");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        user.getRoles().forEach(r ->
                grantedAuthorities.add(new SimpleGrantedAuthority(r.getName())));
        return new CurrentUser(user.getEmail(), user.getPassword(),
                grantedAuthorities, user);
    }

}
