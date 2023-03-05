package com.bayu.employee.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "trainings")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Training implements Serializable {

    private final static long serialVersionUID = 3392266896833837695L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column(name = "training_name")
    private String trainingName;

    @Column(name = "certificate")
    private String certificate;

    @Column(name = "year")
    private Integer year;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "fk_trainings_employee_id"), referencedColumnName = "id_user")
    private Employee employee;
}
