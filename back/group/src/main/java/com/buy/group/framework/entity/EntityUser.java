package com.buy.group.framework.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity(name = "users")
public class EntityUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "device_token")
    private String deviceToken;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "active", nullable = false)
    private Integer active;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    @JsonIgnore
    private EntityCity city;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<EntityRole> roles;

    @ManyToMany(mappedBy = "user")
    private List<EntityBuyer> buyers;

    @ManyToMany(mappedBy = "user")
    private List<EntityCompany> companies;

    public EntityUser() {
    }

    public EntityUser(Long id, String username, String email, String password, String deviceToken, String name,
            Integer active, EntityCity city, List<EntityBuyer> buyers, List<EntityCompany> companies,
            List<EntityRole> roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.deviceToken = deviceToken;
        this.name = name;
        this.active = active;
        this.city = city;
        this.buyers = buyers;
        this.companies = companies;
        this.roles = roles;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceToken() {
        return this.deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getActive() {
        return this.active;
    }

    public void setActive(Integer active) {
        this.active = active;
    }

    public EntityCity getCity() {
        return this.city;
    }

    public void setCity(EntityCity city) {
        this.city = city;
    }

    public List<EntityRole> getRoles() {
        return this.roles;
    }

    public void encryptPassword(PasswordEncoder bcryptEncoder) {
        this.setPassword(bcryptEncoder.encode(this.getPassword()));
    }
}