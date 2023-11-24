package com.bayu.employee.model;

import com.bayu.employee.model.base.UserAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "work_experiences")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperience extends UserAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "position")
    private String position;

    @Column(name = "salary")
    private BigDecimal salary;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "year_of_employment")
    private LocalDateTime yearOfEmployment;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "year_of_resignation")
    private LocalDateTime yearOfResignation;

    // perhitungan lengthOfWork didapatkan dari yearOfResignation - yearOfEmployment
    // lalu dikonversi dalam tahun
    @Column(name = "length_of_work")
    private Float lengthOfWork;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "fk_work_experiences_employee_id"), referencedColumnName = "id_user")
    private Employee employee;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
