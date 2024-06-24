package com.gestone.gestone_api.domain.user;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.UUID;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.gestone.gestone_api.domain.marbleshop.Marbleshop;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "marbleshop_user")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private UserType userType;
    @ManyToOne(fetch = FetchType.LAZY)
    private Marbleshop marbleshop;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public User() {
    }

    
    public User(UUID id, String name, String email, String password, String phone, UserType userType,
            Marbleshop marbleshop) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userType = userType;
        this.marbleshop = marbleshop;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserType getType() {
        return userType;
    }

    public void setType(UserType userType) {
        this.userType = userType;
    }

    public Marbleshop getMarbleshop() {
        return marbleshop;
    }

    public void setMarbleshop(Marbleshop marbleshop) {
        this.marbleshop = marbleshop;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (userType == UserType.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
        if (userType == UserType.EMPLOYEE) return List.of(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
        else return List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER"));
    }


    @Override
    public String getUsername() {
       return email;
    }

}
