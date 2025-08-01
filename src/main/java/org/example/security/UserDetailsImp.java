package org.example.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.model.User;
import org.example.model.role.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDetailsImp implements UserDetails {

    private Long id;
    private String login;
    private String password;
    private String email;
    private Role role;

    public static UserDetailsImp build(User user) {
        return new UserDetailsImp(
                user.getId(),
                user.getLogin(),
                user.getPassword(),
                user.getEmail(),
                user.getRole());

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
