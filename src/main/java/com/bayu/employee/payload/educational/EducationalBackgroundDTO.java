package com.bayu.employee.payload.educational;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EducationalBackgroundDTO {

    private String id;

    private String userId;

    private String name;

    private String major;

    private String year;
}
