package ru.mytyshi.esnp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.mytyshi.esnp.models.Person;
import ru.mytyshi.esnp.repositories.PeopleRepository;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PeopleServiceTest {
    @Mock
    PeopleRepository peopleRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    PeopleService peopleService;

    private Person personMocked;

    @BeforeEach
    void initPersonMocked() {
        personMocked = new Person(10, "Person Mocked", "USER", "00000",
                new HashSet<>(1), new HashSet<>(1));
    }

    @Test
    void registration_Valid() {
        doReturn("12345").when(passwordEncoder).encode(personMocked.getPassword());

        peopleService.registration(personMocked);

        assertEquals("12345", personMocked.getPassword());
        verify(peopleRepository, Mockito.times(1)).save(personMocked);
    }

    @Test
    void getAllPerson_Valid() {
        doReturn(List.of(personMocked)).when(peopleRepository).findAll();

        peopleService.getAllPerson();

        verify(peopleRepository, Mockito.times(1)).findAll();
        assertEquals("Person Mocked", peopleService.getAllPerson().get(0).getUsername());
    }

}