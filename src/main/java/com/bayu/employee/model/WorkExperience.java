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
import java.time.LocalDate;

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
    private LocalDate yearOfEmployment;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "year_of_resignation")
    private LocalDate yearOfResignation;

    // perhitungan lengthOfWork didapatkan dari yearOfResignation - yearOfEmployment
    // lalu dikonversi dalam tahun
    @Column(name = "length_of_work")
    private Float lengthOfWork;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_information_id", foreignKey = @ForeignKey(name = "fk_work_experiences_user_information_id"), referencedColumnName = "id_user")
    private UserInformation userInformation;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

}
