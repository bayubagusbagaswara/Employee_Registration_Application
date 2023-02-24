package com.bayu.employee.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "employees")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column(name = "position")
    private String position;

    @Column(name = "nik")
    private String nik;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private Integer age;

    @Column(name = "place_of_birth")
    private String placeOfBirth;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

//
//    private String religion;
//
//    private String bloodGroup;
//
//    private String maritalStatus;
//
//    @Column(name = "address_ktp")
//    private String addressKtp;
//
//    private String subDistrictKtp; // desa/kelurahan
//
//    private String districtKtp; // kecamatan
//
//    private String cityKtp; // kota
//
//    private String provinceKtp; // provinsi
//
//    @Column(name = "address_domicile")
//    private String addressDomicile;
//
//    private String subDistrictDomicile;
//
//    private String districtDomicile;
//
//    private String cityDomicile;
//
//    private String provinceDomicile;


//    KONTAK TERDEKAT

    // status hubungan
    // private no kontak

    @MapsId
    @OneToOne
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "fk_employee_id_user"), referencedColumnName = "id")
    private User user;

}