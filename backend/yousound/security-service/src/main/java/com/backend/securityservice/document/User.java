package com.backend.securityservice.document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Document
@Data
public class User implements UserDetails {
    @Id
    @Size(min = 2, max = 36)
    private String id;

    @NonNull
    @Size(min=2, max=30)
    private String username;

    @NonNull
    @Size(min=4, max=100)
    private String password;

    @Email
    @Size(max=50)
    private String email;

    @Size(max=30)
    @Pattern(regexp = "^[a-zA-Z]*$", message = "must contain only letters")
    private String firstName;

    @Size(max=30)
    @Pattern(regexp = "^[a-zA-Z]*$", message = "must contain only letters")
    private String lastName;

    private Collection<? extends GrantedAuthority> authorities;

    public User (@NonNull String username, @NonNull String password) {
        this.username = username;
        this.password = password;
    }

    public User() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
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