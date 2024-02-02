package ru.mytyshi.esnp.models;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "main_table")
public class MainTable {

    @Id
    @Column(name = "main_table_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mainTableId;

    @NotNull
    @Column(name = "full_name")
    private String fullName;

    @Column(name = "appointment")
    private String appointment;

    @Column(name = "address")
    private String address;

    @Column(name = "ip")
    private String ip;

    @Column(name = "anyconnect")
    private Boolean anyconnect;

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "who_created_id", referencedColumnName = "person_id")
    private Person personWhoCreated;

    @Column(name = "when_created")
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date whenCreated;

    @ManyToOne
    @JoinColumn(name = "who_changed_id", referencedColumnName = "person_id")
    private Person personWhoChanged;

    @Column(name = "when_changed")
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    private Date whenChanged;

    @JoinColumn(name = "note")
    private String note;

    public MainTable(String fullName, String appointment, String address, String ip, Boolean anyconnect, String phone, String note) {
        this.fullName = fullName;
        this.appointment = appointment;
        this.address = address;
        this.ip = ip;
        this.anyconnect = anyconnect;
        this.phone = phone;
        this.whenCreated = new Date();
        this.note = note;
    }
}
