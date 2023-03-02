package com.bayu.employee.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "work_experiences")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperience {

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

    @Column(name = "length_of_work")
    private Float lengthOfWork; // misal 2.5 tahun

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "fk_work_experiences_employee_id"), referencedColumnName = "id_user")
    private Employee employee;

    // jika ada kolom tahun resign, maka nanti bisa di sorting berdasarkan tahun
    // bisa gak Date tapi cuma Bulan dan Tahun (MM-yyyy)
    @Column(name = "year_of_employment")
    private Integer yearOfEmployment;

    @Column(name = "year_of_resignation")
    private Integer yearOfResignation;

}
