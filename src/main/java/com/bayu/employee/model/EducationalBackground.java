package com.bayu.employee.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "educational_background")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationalBackground {

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "major")
    private String major;

    @Column(name = "year")
    private String year;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(
//            name = "employee_id",
//            foreignKey = @ForeignKey(name = "fk_educational_employee_id"),
//            referencedColumnName = "id",
//            nullable = false)
//    private Employee employee;

}
