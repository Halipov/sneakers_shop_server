package com.sneakers.shop.sneakers_shop.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String article;

    private String name;

    private String description;

    private float price;

    private float size;

    private String photo;

    public Product(String article, String name, String description, float price, float size,
            String photo) {
        this.article = article;
        this.name = name;
        this.description = description;
        this.price = price;
        this.size = size;
        this.photo = photo;
    }

}
