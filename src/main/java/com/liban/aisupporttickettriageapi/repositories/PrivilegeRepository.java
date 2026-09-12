package com.liban.aisupporttickettriageapi.repositories;

import com.liban.aisupporttickettriageapi.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PrivilegeRepository extends JpaRepository<Privilege, Integer> {

    @Query("SELECT privilege FROM Privilege privilege WHERE privilege.privilege=:queryStr")
    Privilege findByPrivilege(String queryStr);
}
