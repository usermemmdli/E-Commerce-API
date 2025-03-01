package com.example.E_Commerce_API.dao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jdk.jfr.Enabled;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Builder
@Table(name = "users")
@Enabled
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Users implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String phoneNumber;
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    String email;
    @Size(min = 6, message = "Password must be at least 6 characters long")
    String password;
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    Roles roles;
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private Set<Reviews> reviews = new HashSet<>();
    @OneToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<Reports> reports;
    Timestamp createdAt;
    Timestamp updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }
}
