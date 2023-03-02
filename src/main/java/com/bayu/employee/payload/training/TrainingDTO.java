package com.bayu.employee.payload.training;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingDTO {

    private String id;

    private String userId;

    private String employeeId;

    private String trainingName;

    private String certificate;

    private String year;
}
