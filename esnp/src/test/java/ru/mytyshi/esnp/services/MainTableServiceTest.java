package ru.mytyshi.esnp.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
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
    @WithUserDetails(value = "Person Mocked")
    void save_Valid() {
        Date currentDate = new Date(1704067200000L);
        Person person1 = new Person(10, "Person Mocked", "USER", "00000",
                new HashSet<>(1), new HashSet<>(1));

        mainTable.setPersonWhoCreated(person1);
        mainTable.setPersonWhoChanged(person1);
        mainTable.setWhenCreated(currentDate);
        mainTable.setWhenChanged(currentDate);

        doReturn(mainTable).when(mainTableRepository).save(mainTable);

        mainTableService.save(mainTable);

        verify(mainTableService, Mockito.times(1)).save(mainTable);
    }
}
