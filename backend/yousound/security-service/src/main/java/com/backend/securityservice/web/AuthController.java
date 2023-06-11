package com.backend.securityservice.web;

import com.backend.securityservice.document.User;
import com.backend.securityservice.dto.LoginDTO;
import com.backend.securityservice.dto.SignupDTO;
import com.backend.securityservice.dto.TokenDTO;
import com.backend.securityservice.dto.UserDTO;
import com.backend.securityservice.security.TokenGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.Produces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*")
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
    @Autowired
    private RabbitTemplate rabbitTemplate;


    @PostMapping("/register")
    public ResponseEntity<TokenDTO> register(@Validated @RequestBody SignupDTO signupDTO) {
        User user = new User(signupDTO.getUsername(), signupDTO.getPassword());
        user.setEmail(signupDTO.getEmail());
        user.setFirstName(signupDTO.getFirstName());
        user.setLastName(signupDTO.getLastName());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        user.setAuthorities(authorities);
        userDetailsManager.createUser(user);

        Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(user, signupDTO.getPassword(), authorities);

        return ResponseEntity.ok(tokenGenerator.createToken(authentication));
    }

    @PostMapping("/register/admin")
    public ResponseEntity<TokenDTO> registerAdmin(@Validated @RequestBody SignupDTO signupDTO) {
        User user = new User(signupDTO.getUsername(), signupDTO.getPassword());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        user.setAuthorities(authorities);
        userDetailsManager.createUser(user);

        Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(user, signupDTO.getPassword(), authorities);

        return ResponseEntity.ok(tokenGenerator.createToken(authentication));
    }

    @PostMapping("/register/artist")
    public ResponseEntity<TokenDTO> registerArtist(@Validated @RequestBody SignupDTO signupDTO) {
        User user = new User(signupDTO.getUsername(), signupDTO.getPassword());
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ARTIST"));
        user.setAuthorities(authorities);
        userDetailsManager.createUser(user);

        Authentication authentication = UsernamePasswordAuthenticationToken.authenticated(user, signupDTO.getPassword(), authorities);
        ObjectMapper objectMapper = new ObjectMapper();
        String message;
        try {
            message = objectMapper.writeValueAsString(signupDTO);
        } catch (JsonProcessingException e) {
            // Handle the exception as needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        // Publish the serialized message to the RabbitMQ queue

        rabbitTemplate.convertAndSend("user-service-queue", message);
        return ResponseEntity.ok(tokenGenerator.createToken(authentication));
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> login(@Validated @RequestBody LoginDTO loginDTO) {
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
    public ResponseEntity<TokenDTO> token(@RequestBody TokenDTO tokenDTO) {
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

    @GetMapping("/role")
    public ResponseEntity<String> getRole(@RequestParam("token") String token) {
        Jwt jwt = tokenGenerator.decodeAccessToken(token);
        String role = jwt.getClaimAsString("roles");
        role = role.substring(1, role.length() - 1);
        return ResponseEntity.ok(role);
    }
}