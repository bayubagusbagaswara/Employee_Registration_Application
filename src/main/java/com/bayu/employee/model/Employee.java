package com.bayu.employee.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "employees")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Where(clause = "is_deleted = false")
public class Employee implements Serializable {

    @Serial
    private final static long serialVersionUID = -3086674995898498716L;

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

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private Integer age;

    @Column(name = "place_of_birth")
    private String placeOfBirth;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "salary")
    private BigDecimal salary;

    // tapi ketika isi form createEmployee maka mengisi juga object RiwayatPendidikan
    // tingkat pendidikan terakhir
    // jurusan
    // nama instansi

    @Column(name = "level_of_education")
    private String levelOfEducation;

    @Column(name = "department")
    private String department;

    @Column(name = "college_name")
    private String collegeName;

    @Column(name = "graduation_year")
    private Integer graduationYear;

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

//    @Column(name = "is_deleted")
//    private boolean isDeleted;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "fk_employee_id_user"), referencedColumnName = "id")
    private User user;

    // OneToMany dengan Education
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Education> educations = new HashSet<>();

    // OneToMany dengan Training
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<Training> trainings = new HashSet<>();

    // OneToMany dengan WorkExperience
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<WorkExperience> workExperiences = new HashSet<>();

}