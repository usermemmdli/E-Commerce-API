package com.example.E_Commerce_API.dao.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Enabled
@Data
@Document(collection = "products")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Products {
    @Id
    String id;
    String name;
    String price;
    String city;
    String brand;
    String model;
    Boolean delivery;
    Boolean status;
    String description;
    String categoriesName;
    List<String> reviewsId = new ArrayList<>();
    List<String> reportsId = new ArrayList<>();
    Long usersId;
    Date createdAt;
    Date updatedAt;
    String imageUrl;
}
