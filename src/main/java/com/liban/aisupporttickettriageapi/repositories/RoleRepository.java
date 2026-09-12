package com.liban.aisupporttickettriageapi.repositories;

import com.liban.aisupporttickettriageapi.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT role FROM Role role WHERE role.role=:queryStr")
    Role findByRole(String queryStr);
}
