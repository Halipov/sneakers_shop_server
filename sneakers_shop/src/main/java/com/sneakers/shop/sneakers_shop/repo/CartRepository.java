package com.sneakers.shop.sneakers_shop.repo;

import java.util.Optional;

import com.sneakers.shop.sneakers_shop.models.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findById(long id);
}
