package com.bayu.employee.service.impl;

import com.bayu.employee.model.Role;
import com.bayu.employee.model.enumerator.RoleName;
import com.bayu.employee.payload.CreateRoleRequest;
import com.bayu.employee.payload.RoleDTO;
import com.bayu.employee.repository.RoleRepository;
import com.bayu.employee.service.RoleService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

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
        role.setName(RoleName.valueOf(createRoleRequest.getName().toUpperCase()));

        role.setCreatedAt(Instant.now());
        role.setCreatedBy("SYSTEM");

        roleRepository.save(role);

        return RoleDTO.builder()
                .id(String.valueOf(role.getId()))
                .name(String.valueOf(role.getName()))
                .createdAt(formattedInstantToString(role.getCreatedAt()))
                .updatedAt(role.getUpdatedAt() == null ? null : formattedInstantToString(role.getUpdatedAt()))
                .build();
    }

    @Override
    public RoleDTO getRoleByName(String name) {
        Role role = roleRepository.getRoleByName(name)
                .orElseThrow(() -> new RuntimeException("Role not found with name : [" + name + "]"));
        return mapToRoleDTO(role);
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        List<Role> roleList = roleRepository.getAllRoles();
        return mapToRoleDTOList(roleList);
    }

    @Override
    public void deleteRole(String roleId) {
        Role role = roleRepository.findById(Long.valueOf(roleId))
                .orElseThrow(() -> new RuntimeException("Role not found with id : [" + roleId + "]"));

        role.setDeleted(true);
        roleRepository.save(role);
    }

    private static RoleDTO mapToRoleDTO(Role role) {
        return RoleDTO.builder()
                .id(String.valueOf(role.getId()))
                .name(role.getName().getRoleName())
                .createdAt(formattedInstantToString(role.getCreatedAt()))
                .updatedAt(role.getUpdatedAt() == null ? null : formattedInstantToString(role.getUpdatedAt()))
                .build();
    }

    private static List<RoleDTO> mapToRoleDTOList(List<Role> roleList) {
        return roleList.stream()
                .map(RoleServiceImpl::mapToRoleDTO)
                .collect(Collectors.toList());
    }

}
