package com.bayu.employee.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "educational_background")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationalBackground implements Serializable {

    private final static long serialVersionUID = -292203108165354119L;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "fk_educations_employee_id"), referencedColumnName = "id_user")
    private Employee employee;

}
