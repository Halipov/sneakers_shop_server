package com.sneakers.shop.sneakers_shop.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.sneakers.shop.sneakers_shop.models.Product;
import com.sneakers.shop.sneakers_shop.models.Role;
import com.sneakers.shop.sneakers_shop.models.User;
import com.sneakers.shop.sneakers_shop.models.UserInfo;
import com.sneakers.shop.sneakers_shop.models.UserRole;
import com.sneakers.shop.sneakers_shop.repo.ProductRepository;
import com.sneakers.shop.sneakers_shop.repo.RoleRepository;
import com.sneakers.shop.sneakers_shop.repo.UserInfoRepository;
import com.sneakers.shop.sneakers_shop.repo.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository repo;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    ProductRepository productRepo;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUser() {
        try {
            List<User> users = new ArrayList<>(repo.findAll());

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/userInfo/{id}")
    public ResponseEntity<UserInfo> getUserById(@PathVariable("id") long id) {
        Optional<UserInfo> userData = userInfoRepository.findById(id);

        return userData.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/userInfo/{id}")
    public ResponseEntity<UserInfo> updateUserInfo(@PathVariable("id") long id, @RequestBody UserInfo userInfo) {
        Optional<UserInfo> userData = userInfoRepository.findById(id);

        if (userData.isPresent()) {
            return new ResponseEntity<>(userInfoRepository.save(userInfo), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/addFavorite/{id}")
    public ResponseEntity<List<Product>> addFavorite(@PathVariable("id") long id, @RequestBody Product product) {
        Optional<UserInfo> userData = userInfoRepository.findById(id);
        if (userData.isPresent()) {
            Optional<Product> _product = productRepo.findById(product.getId());
            UserInfo userInfo = userData.get();
            if (_product.isPresent()) {
                Product productTemp = _product.get();
                if (userInfo.getProduct().contains(productTemp)) {
                    userInfo.getProduct().remove(productTemp);
                } else {
                    userInfo.getProduct().add(productTemp);
                }
                userInfoRepository.save(userInfo);
            } else {
                return new ResponseEntity<>(userInfo.getProduct(), HttpStatus.OK);
            }
            return new ResponseEntity<>(userInfo.getProduct(), HttpStatus.OK);

        } else

        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUserByUsername(@RequestParam(required = false) String username,
            @RequestParam(required = false) String email) {
        Optional<User> userData = Optional.empty();
        if (username != null) {
            userData = repo.findByUsername(username);
        }
        return userData.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        Optional<User> userData = repo.findById(id);

        if (userData.isPresent()) {
            User _user = userData.get();
            return new ResponseEntity<>(repo.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/user/{id}/role/")
    public ResponseEntity<User> updateUserRole(@PathVariable("id") long id, @RequestBody boolean admin) {
        Optional<User> userData = repo.findById(id);
        if (userData.isPresent()) {
            User _user = userData.get();
            Set<Role> roles = new HashSet<>();

            Role userRole = roleRepository.findByName(UserRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
            if (admin) {
                Role adminRole = roleRepository.findByName(UserRole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(adminRole);
            }
            _user.setRoles(roles);
            return new ResponseEntity<>(repo.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/user/{id}")
    // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id) {
        try {
            repo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
