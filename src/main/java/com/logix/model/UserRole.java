package com.logix.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name="user_roles", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class UserRole implements Serializable{
    public static final long serialVersion = 1L;

    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="id", unique = true, nullable = false)
    private String id;

    @Column(name="role_name")
    private String role;

    @ManyToOne
    private User user;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
