package com.bayu.employee.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "educations")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Education {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column(name = "level_of_education")
    private String levelOfEducation;

    @Column(name = "department")
    private String department;

    @Column(name = "college_name")
    private String collegeName;

    @Column(name = "graduation_year")
    private Integer graduationYear;

    // karena di table Employee adalah OneToOne dengan table User, dan menggunakan id_user, maka kita referencedColumn nya adalah id_user
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "fk_educational_employee_id"), referencedColumnName = "id_user")
//    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "fk_educations_id_user"), referencedColumnName = "id")
    private User user;

}
