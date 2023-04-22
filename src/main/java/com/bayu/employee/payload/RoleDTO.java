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
public class RoleDTO implements Serializable {

    private final static long serialVersionUID = -7733161794979716713L;

    private String id;

    private String name;

    private String createdAt;

    private String updatedAt;
}
