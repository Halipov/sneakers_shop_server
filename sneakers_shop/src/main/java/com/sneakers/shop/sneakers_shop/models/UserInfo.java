package com.sneakers.shop.sneakers_shop.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@Table(name = "user_info")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String phone;

    private String address;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> product;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userCart_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Cart cart;

    public UserInfo(Long id, String firstName, String lastName, String phone, String address, List<Product> product,
            Cart cart) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.address = address;
        this.product = product;
        this.cart = cart;
    }

    public UserInfo(Long id) {
        this.id = id;

    }
}
