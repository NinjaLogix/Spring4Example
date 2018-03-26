package com.logix.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;

import java.io.Serializable;
import java.util.Set;

@Entity(name="ForeignKeyAssoEntity")
@Table(name="user", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class User implements Serializable{
    public static final long serialVersion = 1L;

    @Id
    @GeneratedValue
    @Column(name="id", unique = true, nullable = false)
    private int id;

    @Column(name="uname")
    private String userName;

    @Column(name="email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="usr_sur")
    private Set<UserRole> roles;

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }

    public void setUname(String uname) { this.userName = uname; }
    public String getUname() { return userName; }

    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return email; }

    public void setRoles(Set<UserRole> roles) { this.roles = roles; }
    public Set<UserRole> getRoles() { return roles; }
}
