package com.example.E_Commerce_API.dao.entity;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

@Builder
@Document(collection = "reports")
@Enabled
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Reports {
    @Id
    String id;
    Long usersId;
    String productsId;
    String description;
    Timestamp createdAt;
    Timestamp updatedAt;
    Boolean status;
}
