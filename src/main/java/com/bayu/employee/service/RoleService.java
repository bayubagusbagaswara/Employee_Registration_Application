package com.bayu.employee.service;

import com.bayu.employee.payload.CreateRoleRequest;
import com.bayu.employee.payload.RoleDTO;

public interface RoleService {

    RoleDTO createRole(CreateRoleRequest createRoleRequest);
}
