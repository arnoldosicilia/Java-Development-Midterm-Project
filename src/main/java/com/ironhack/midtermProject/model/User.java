package com.ironhack.midtermProject.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @NotNull
    protected String name;
    @NotNull
    protected String username;
    @NotNull
    protected String password;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Role> roles = new HashSet<>();

    /** Constructors **/
    public User() {}

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    /** Getters & Setters **/
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}
    public String getPassword() {return password;}
    public void setPassword(String password) {this.password = password;}
    public Set<Role> getRoles() {return roles;}
    public void setRoles(Set<Role> roles) {this.roles = roles;}

    @Override
    public String toString() {
        return  id + "\n" +
                username + "\n" +
                name + "\n" ;
    }

}
