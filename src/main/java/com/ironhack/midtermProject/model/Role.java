package com.ironhack.midtermProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ironhack.midtermProject.enums.SystemRole;

import javax.persistence.*;

@Entity
@Table
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(value = EnumType.STRING)
    private SystemRole role;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    /** Constructors **/
    public Role() {}

    public Role(SystemRole role, User user) {
        this.role = role;
        this.user = user;
    }

    /** Getters & Setters **/
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public SystemRole getRole() {
        return role;
    }
    public void setRole(SystemRole role) {
        this.role = role;
    }
    public User getUser() {return user;}
    public void setUser(User user) {this.user = user;}

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", authority='" + role + '\'';
    }
}
