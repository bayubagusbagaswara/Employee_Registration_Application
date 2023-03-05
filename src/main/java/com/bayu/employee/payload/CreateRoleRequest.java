package com.bayu.employee.payload;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateRoleRequest implements Serializable {

    private final static long serialVersionUID = -8485701433951437225L;

    private String id;
    private String name;
}
