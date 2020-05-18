package com.buy.group.framework.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name = "roles")
public class EntityRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<EntityUser> users;

    public EntityRole() {
    }

    public EntityRole(Long id, String name, List<EntityUser> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EntityUser> getUsers() {
        return this.users;
    }

    public void setUsers(List<EntityUser> users) {
        this.users = users;
    }

}