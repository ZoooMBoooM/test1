package ru.mytyshi.esnp.repositories;

import ru.mytyshi.esnp.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PeopleRepository extends JpaRepository<Person, Integer> {

    Optional<Person>findByUsername(String name);
}
