package com.bayu.employee.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "name")
    private String name;

    @Column(name = "gender")
    private String gender;

    @Column(name = "age")
    private Integer age;

    @Column(name = "address")
    private String address;

    // Employee memilik relasi OneToOne dengan User
    @MapsId
    @OneToOne
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "fk_employee_id_user"), referencedColumnName = "id")
    private User user;

//    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<EducationalBackground> educationalBackgrounds = new HashSet<>();
}