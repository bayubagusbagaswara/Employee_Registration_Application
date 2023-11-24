package com.bayu.employee.model;

import com.bayu.employee.model.base.UserAudit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "educational_backgrounds")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationalBackground extends UserAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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
