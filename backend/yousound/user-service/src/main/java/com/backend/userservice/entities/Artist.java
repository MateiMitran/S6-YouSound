package com.backend.userservice.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;


@Data
@Document
public class Artist extends User {

    private String description;

    private int monthly_listeners;


    public Artist(String username, String password, String description, int monthly_listeners) {
        super(username, password);
        this.description = description;
        this.monthly_listeners = monthly_listeners;
    }

    public Artist() {

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMonthly_listeners() {
        return monthly_listeners;
    }

    public void setMonthly_listeners(int monthly_listeners) {
        this.monthly_listeners = monthly_listeners;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Artist artist = (Artist) o;
        return monthly_listeners == artist.monthly_listeners && Objects.equals(description, artist.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description, monthly_listeners);
    }

    @Override
    public String toString() {
        return "Artist{" +
                "description='" + description + '\'' +
                ", monthly_listeners=" + monthly_listeners +
                '}';
    }
}
