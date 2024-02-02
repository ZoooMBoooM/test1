package ru.mytyshi.esnp.services;

import lombok.extern.slf4j.Slf4j;
import ru.mytyshi.esnp.models.MainTable;
import ru.mytyshi.esnp.repositories.MainTableRepository;
import ru.mytyshi.esnp.repositories.PeopleRepository;
import ru.mytyshi.esnp.security.PersonDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@Slf4j
public class MainTableService {

    private final MainTableRepository mainTableRepository;

    private final PeopleRepository peopleRepository;

    public MainTableService(MainTableRepository mainTableRepository, PeopleRepository peopleRepository) {
        this.mainTableRepository = mainTableRepository;
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public void save(MainTable mainTable) {
        Date currentDate = new Date(System.currentTimeMillis());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        mainTable.setPersonWhoCreated(personDetails.getPerson());
        mainTable.setPersonWhoChanged(personDetails.getPerson());
        mainTable.setWhenCreated(currentDate);
        mainTable.setWhenChanged(currentDate);

        mainTableRepository.save(mainTable);
    }

    public List<MainTable> getAllMainTable() {
        return mainTableRepository.findAll();
    }

    public MainTable findOne(int id) {
        Optional<MainTable> foundRecordOfMainTable = mainTableRepository.findById(id);
        return foundRecordOfMainTable.orElseThrow();
    }
    @Transactional
    public void delete(int id) {
        mainTableRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, MainTable updatedMainTable) {
        updatedMainTable.setMainTableId(id);
        mainTableRepository.save(updatedMainTable);
    }
}
