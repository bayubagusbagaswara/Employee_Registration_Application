package com.bayu.employee.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * model untuk Offering
 */
@Entity
@Table(name = "offers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Offer implements Serializable {

    @Serial
    private final static long serialVersionUID = 8882264240778484657L;

    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    private String id;

    @Column(name = "expectation_salary")
    private BigDecimal expectationSalary;

    // bersedia ditempatkan dimana saja, Ya atau Tidak
    @Column(name = "agreed_placement")
    private String agreedPlacement;

    @MapsId
    @OneToOne
    @JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "fk_offer_id_user"), referencedColumnName = "id")
    private User user;

}
