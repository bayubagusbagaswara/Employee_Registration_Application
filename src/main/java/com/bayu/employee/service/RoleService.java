package com.bayu.employee.service;

import com.bayu.employee.payload.CreateRoleRequest;
import com.bayu.employee.payload.RoleDTO;

import java.util.List;

public interface RoleService {

    RoleDTO createRole(CreateRoleRequest createRoleRequest);

    RoleDTO getRoleByName(String name);

    List<RoleDTO> getAllRoles();

    void deleteRole(String roleId);
}
