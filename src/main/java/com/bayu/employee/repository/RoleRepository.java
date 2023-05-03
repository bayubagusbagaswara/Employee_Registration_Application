package com.bayu.employee.repository;

import com.bayu.employee.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query(value = "SELECT * FROM Roles r WHERE LOWER(r.name) = LOWER(:name) AND r.is_deleted = false", nativeQuery = true)
    Optional<Role> getRoleByName(@RequestParam(name = "name") String name);

    @Query(value = "SELECT * FROM Roles r WHERE r.is_deleted = false", nativeQuery = true)
    List<Role> getAllRoles();

}
