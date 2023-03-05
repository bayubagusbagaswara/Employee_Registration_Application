package com.bayu.employee.payload.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateTrainingRequest implements Serializable {

    private final static long serialVersionUID = -489891877436701937L;

    private String trainingName;

    private String certificate;

    private String year;

}
