package com.backend.securityservice.web;

import com.backend.securityservice.document.User;
import com.backend.securityservice.dto.LoginDTO;
import com.backend.securityservice.dto.SignupDTO;
import com.backend.securityservice.dto.TokenDTO;
import com.backend.securityservice.dto.UserDTO;
import com.backend.securityservice.security.TokenGenerator;
import jakarta.ws.rs.Produces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {


    Logger log = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    UserDetailsManager userDetailsManager;
    @Autowired
    TokenGenerator tokenGenerator;
    @Autowired
    DaoAuthenticationProvider daoAuthenticationProvider;
    @Autowired
    JwtAuthenticationProvider refreshTokenAuthProvider;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody SignupDTO signupDTO) {
        User user = new User(signupDTO.getUsername(), signupDTO.getPassword());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        user.setAuthorities(authorities);
        userDetailsManager.createUser(user);

        Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(user, signupDTO.getPassword(), authorities);

        return ResponseEntity.ok(tokenGenerator.createToken(authentication));
    }

    @PostMapping("/register/admin")
    public ResponseEntity registerAdmin(@RequestBody SignupDTO signupDTO) {
        User user = new User(signupDTO.getUsername(), signupDTO.getPassword());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        user.setAuthorities(authorities);
        userDetailsManager.createUser(user);

        Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(user, signupDTO.getPassword(), authorities);

        return ResponseEntity.ok(tokenGenerator.createToken(authentication));
    }

    @PostMapping("/register/artist")
    public ResponseEntity registerArtist(@RequestBody SignupDTO signupDTO) {
        User user = new User(signupDTO.getUsername(), signupDTO.getPassword());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        user.setAuthorities(authorities);
        userDetailsManager.createUser(user);

        Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(user, signupDTO.getPassword(), authorities);

        return ResponseEntity.ok(tokenGenerator.createToken(authentication));
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDTO loginDTO) {
        log.info("Received login request for user: {}", loginDTO.getUsername());

        try {
            Authentication authentication = daoAuthenticationProvider.authenticate(UsernamePasswordAuthenticationToken.unauthenticated(loginDTO.getUsername(), loginDTO.getPassword()));
            log.info("Successfully authenticated user: {}", loginDTO.getUsername());

            TokenDTO token = tokenGenerator.createToken(authentication);
            log.info("Successfully generated token for user: {}", loginDTO.getUsername());

            return ResponseEntity.ok(token);

        } catch (AuthenticationException e) {
            log.error("Failed to authenticate user: {}", loginDTO.getUsername(), e);
            throw e;

        } catch (Exception e) {
            log.error("An unexpected error occurred while processing login for user: {}", loginDTO.getUsername(), e);
            throw e;
        }
    }

    @PostMapping("/token")
    public ResponseEntity token(@RequestBody TokenDTO tokenDTO) {
        Authentication authentication = refreshTokenAuthProvider.authenticate(new BearerTokenAuthenticationToken(tokenDTO.getRefreshToken()));
        Jwt jwt = (Jwt) authentication.getCredentials();
        return ResponseEntity.ok(tokenGenerator.createToken(authentication));
    }

    @GetMapping("/validate")
    @Produces(MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDTO> validate(@RequestParam("token") String token) {
        boolean isValid = tokenGenerator.isAccessTokenValid(token);
        Jwt jwt = tokenGenerator.decodeAccessToken(token);
        if (!isValid) {
            return null;
        }
        return ResponseEntity.ok(new UserDTO(jwt.getSubject(), jwt.getClaim("username")));
    }
}