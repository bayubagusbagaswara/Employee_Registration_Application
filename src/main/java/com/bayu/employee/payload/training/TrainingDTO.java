package com.bayu.employee.payload.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingDTO implements Serializable {

    @Serial
    private final static long serialVersionUID = 7931320846186695196L;

    private String id;

    private String userId;

    private String employeeId;

    private String trainingName;

    private String certificate;

    private String year;
}
