package com.sneakers.shop.sneakers_shop.repo;

import java.util.List;
import java.util.Optional;

import com.sneakers.shop.sneakers_shop.models.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(long id);

    // @Query(value = "SELECT DISTINCT article FROM product", nativeQuery = true)
    @Query(value = "SELECT * FROM product GROUP BY article", nativeQuery = true)
    List<Product> findUniqueArticle();

}
