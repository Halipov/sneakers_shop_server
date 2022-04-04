package com.sneakers.shop.sneakers_shop.repo;

import com.sneakers.shop.sneakers_shop.models.Role;
import com.sneakers.shop.sneakers_shop.models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRole name);
}
