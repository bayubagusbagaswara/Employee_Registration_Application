package com.bayu.employee.model;

import com.bayu.employee.model.base.UserAudit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "educational_backgrounds")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationalBackground extends UserAudit {

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
    @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "fk_educational_backgrounds_employee_id"), referencedColumnName = "id_user")
    private Employee employee;

}
