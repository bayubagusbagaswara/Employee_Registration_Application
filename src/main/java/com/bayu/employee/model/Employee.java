package com.bayu.employee.model;

import com.bayu.employee.model.base.UserAudit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employees")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@Where(clause = "is_deleted = false")
public class Employee extends UserAudit {

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

    // OFFERING

//    @Column(name = "expectation_salary")
//    private BigDecimal expectationSalary;
//
//    // bersedia ditempatkan dimana saja, Ya atau Tidak
//    @Column(name = "agreed_placement")
//    private String agreedPlacement;


//    KONTAK TERDEKAT

    // status hubungan
    // private no kontak

//    @Column(name = "is_deleted")
//    private boolean isDeleted;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "fk_employee_id_user"), referencedColumnName = "id")
    private User user;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<EducationalBackground> educationalBackgrounds = new HashSet<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<TrainingHistory> trainingHistories = new HashSet<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<WorkExperience> workExperiences = new HashSet<>();

}