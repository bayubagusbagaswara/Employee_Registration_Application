package com.bayu.employee.payload.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateTrainingRequest {

    private String trainingName;

    private Boolean certificate;

    private String year;
}
