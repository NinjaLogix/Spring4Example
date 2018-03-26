package com.logix.model;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.UniqueConstraint;

import java.io.Serializable;

@Entity(name="ForeignKeyAssoAccountEntity")
@Table(name="user_roles", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class UserRole implements Serializable{
    public static final long serialVersion = 1L;

    @Id
    @GeneratedValue
    @Column(name="id", unique = true, nullable = false)
    private int id;

    @Column(name="role_name")
    private String role;

    @ManyToOne
    private User user;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
