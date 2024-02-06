package ru.mytyshi.esnp.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.mytyshi.esnp.models.Person;
import ru.mytyshi.esnp.repositories.PeopleRepository;

import java.util.HashSet;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PersonDetailsServiceTest {
    @Mock
    PeopleRepository peopleRepository;

    @InjectMocks
    PersonDetailsService personDetailsService;

    @Test
    void loadUserByUsername_Valid() {
        Person personMocked = new Person(10, "Person Mocked", "USER", "00000",
                new HashSet<>(1), new HashSet<>(1));
        Optional<Person> optionalPersonMocked = Optional.of(personMocked);
        doReturn(optionalPersonMocked).when(peopleRepository).findByUsername("Person Mocked");

        personDetailsService.loadUserByUsername("Person Mocked");

        assertEquals(10, optionalPersonMocked.get().getPersonId());
        verify(peopleRepository, Mockito.times(1)).findByUsername("Person Mocked");
    }

    @Test
    void loadUserByUsername_Invalid() {
        Optional<Person> person = Optional.empty();
        doReturn(person).when(peopleRepository).findByUsername("Person Mocked");

        assertThrows(UsernameNotFoundException.class, () -> personDetailsService.loadUserByUsername("Person Mocked"));
    }
}
