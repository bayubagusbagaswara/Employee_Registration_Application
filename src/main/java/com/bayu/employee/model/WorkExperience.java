package com.bayu.employee.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "work_experiences")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperience implements Serializable {

    @Serial
    private final static long serialVersionUID = -6814124251031923072L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "position")
    private String position;

    @Column(name = "salary")
    private BigDecimal salary;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "year_of_employment")
    private LocalDate yearOfEmployment;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "year_of_resignation")
    private LocalDate yearOfResignation;

    // perhitungan lengthOfWork didapatkan dari yearOfResignation - yearOfEmployment
    // lalu dikonversi dalam tahun
//    @Column(name = "length_of_work")
//    private Float lengthOfWork;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "fk_work_experiences_employee_id"), referencedColumnName = "id_user")
    private Employee employee;


}
