package com.bayu.employee.service.impl;

import com.bayu.employee.model.Role;
import com.bayu.employee.model.enumerator.RoleName;
import com.bayu.employee.payload.CreateRoleRequest;
import com.bayu.employee.payload.RoleDTO;
import com.bayu.employee.repository.RoleRepository;
import com.bayu.employee.service.RoleService;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static com.bayu.employee.util.StringUtil.formattedInstantToString;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDTO createRole(CreateRoleRequest createRoleRequest) {
        Role role = new Role();
        role.setId(createRoleRequest.getId().toLowerCase());
        role.setName(RoleName.valueOf(createRoleRequest.getName().toUpperCase()));

        Instant now = Instant.now();
        role.setCreatedAt(now);
        role.setCreatedBy("SYSTEM");
        role.setUpdatedAt(now);
        role.setUpdatedBy("SYSTEM");

        roleRepository.save(role);

        return RoleDTO.builder()
                .id(String.valueOf(role.getId()))
                .name(String.valueOf(role.getName()))
                .createdAt(formattedInstantToString(role.getCreatedAt()))
                .updatedAt(formattedInstantToString(role.getUpdatedAt()))
                .build();
    }

}
