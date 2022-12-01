package aaa.tavern.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import aaa.tavern.entity.Role;
import aaa.tavern.dao.PlayerRepository;
import aaa.tavern.entity.Player;

@Service("userDetailsService")
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private PlayerRepository playerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Player> optUser = playerRepository.findByEmail(email);
        if (optUser.isEmpty())
            throw new UsernameNotFoundException("Email not found");

        Player user = optUser.get();

        List<GrantedAuthority> autorities = user
                .getRoles()
                .stream()
                .flatMap(this::transformRoleAsStrings)
                .toList();

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                user.isEnabled(), // Enable
                true, // account not expired
                true, // credentials not expired
                true, // account not locked
                autorities);
    }

    /**
     * Transforme les rôles en un ensemble de chaînes de caractères.
     * 
     * Une entrée par rôle et une entrée par privilège
     * 
     * @param role
     * @return
     */
    private Stream<GrantedAuthority> transformRoleAsStrings(Role role) {
        int size = role.getPrivileges().size() + 1;
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>(size);

        authorities.add(new SimpleGrantedAuthority(role.getName()));

        return authorities.stream();
    }
}
