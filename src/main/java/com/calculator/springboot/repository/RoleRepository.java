package com.calculator.springboot.repository;

import com.calculator.springboot.entity.ERole;
import com.calculator.springboot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(ERole name);
}
