package com.example.E_Commerce_API.dao.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Table(name = "products")
@Enabled
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String price;
    String city;
    String brand;
    String model;
    Boolean delivery;
    Boolean status;
    String description;
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    Categories categories;
    @OneToMany(mappedBy = "products", cascade = CascadeType.ALL)
    private Set<Reviews> reviews = new HashSet<>();
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.EAGER)
    Users users;
    Timestamp createdAt;
    Timestamp updatedAt;
    String imageUrl;
}
