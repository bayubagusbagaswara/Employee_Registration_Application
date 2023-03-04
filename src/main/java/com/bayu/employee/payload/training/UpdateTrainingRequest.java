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
public class UpdateTrainingRequest implements Serializable {

    @Serial
    private final static long serialVersionUID = 3331025117364120280L;

    private String trainingName;

    private String certificate;

    private String year;
}
