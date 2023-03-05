package com.bayu.employee.model;

import com.bayu.employee.model.enumerator.RoleName;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "roles", uniqueConstraints = @UniqueConstraint(name = "role_name_unique", columnNames = "name"))
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

    private final static long serialVersionUID = -3116405158206093317L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 20, nullable = false)
    private RoleName name;
}
