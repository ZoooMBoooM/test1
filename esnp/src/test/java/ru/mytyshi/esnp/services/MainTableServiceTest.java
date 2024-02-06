package ru.mytyshi.esnp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.mytyshi.esnp.models.MainTable;
import ru.mytyshi.esnp.models.Person;
import ru.mytyshi.esnp.repositories.MainTableRepository;
import ru.mytyshi.esnp.repositories.PeopleRepository;

import java.util.Date;
import java.util.HashSet;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MainTableServiceTest {
    @Mock
    MainTableRepository mainTableRepository;

    @Mock
    PeopleRepository peopleRepository;

    @InjectMocks
    MainTableService mainTableService;

    private MainTable mainTable;

    @BeforeEach
    void initMainTable() {
        mainTable = new MainTable("Ivanov", "Appointment", "Address","10.0.20.100",
                false, "+7999-123-0000", "Note");
    }

    @Test
    void save_Valid() {
        Date currentDate = new Date(1704067200000L);
        Person personMocked = new Person(10, "Person Mocked", "USER", "00000",
                new HashSet<>(1), new HashSet<>(1));
        mainTable.setPersonWhoCreated(personMocked);
        mainTable.setPersonWhoChanged(personMocked);
        mainTable.setWhenCreated(currentDate);
        mainTable.setWhenChanged(currentDate);
        doNothing().when(mainTableRepository).save(mainTable);

        mainTableService.save(mainTable);

        verify(mainTableRepository, Mockito.times(1)).save(mainTable);
    }
}
