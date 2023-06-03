package com.backend.userservice.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import jakarta.validation.constraints.*;


@Document(collection = "artists")
@Data
@Getter
@Setter
public class Artist {

    @Id
    private String id;

    @Size(min = 2, max = 30)
    private String username;

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

    public Artist() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artist artist = (Artist) o;
        return Objects.equals(id, artist.id) && Objects.equals(username, artist.username) && Objects.equals(password, artist.password) && Objects.equals(email, artist.email) && Objects.equals(firstName, artist.firstName) && Objects.equals(lastName, artist.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, email, firstName, lastName);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
