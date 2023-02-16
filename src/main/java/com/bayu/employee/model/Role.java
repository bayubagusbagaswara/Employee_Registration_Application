package com.bayu.employee.model;

import com.bayu.employee.model.enumerator.RoleName;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "roles", uniqueConstraints = @UniqueConstraint(name = "role_name_unique", columnNames = "name"))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 20, nullable = false)
    private RoleName name;
}
