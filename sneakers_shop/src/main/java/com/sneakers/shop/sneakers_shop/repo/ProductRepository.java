package com.sneakers.shop.sneakers_shop.repo;

import java.util.Optional;

import com.sneakers.shop.sneakers_shop.models.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(long id);
}
