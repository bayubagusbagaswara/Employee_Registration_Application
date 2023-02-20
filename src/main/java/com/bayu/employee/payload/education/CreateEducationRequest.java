package com.bayu.employee.payload.education;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEducationRequest {

    private String name;

    private String major;

    private String year;
}
