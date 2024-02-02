package ru.mytyshi.esnp.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name="person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personId;

    @NotNull
    @Column(name="username")
    private String username;

    @NotNull
    @Column(name="role")
    private String role;

    @NotNull
    @Column(name="password")
    private String password;

    @Column(name="who_created_id")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "personWhoCreated")
    private Set<MainTable> whoCreated = new HashSet<>();

    @Column(name="who_changed_id")
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "personWhoChanged")
    private Set<MainTable> whoChanged = new HashSet<>();

    Person(String username, String role, String password) {
        this.username = username;
        this.role = role;
        this.password = password;
    }
}
