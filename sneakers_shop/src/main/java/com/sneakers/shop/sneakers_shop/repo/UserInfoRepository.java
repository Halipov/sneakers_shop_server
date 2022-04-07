package com.sneakers.shop.sneakers_shop.repo;

import java.util.Optional;

import com.sneakers.shop.sneakers_shop.models.UserInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findById(long id);
}
