package com.backend.userservice.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;


@Data
@Document
public class Listener extends User {

    private String test;


    public Listener(String username, String password, String test) {
        super(username, password);
        this.test = test;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Listener listener = (Listener) o;
        return Objects.equals(test, listener.test);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), test);
    }

    @Override
    public String toString() {
        return "Listener{" +
                "test='" + test + '\'' +
                '}';
    }
}
