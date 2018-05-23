package com.logix.model;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;

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

    @Column(name="fname")
    private String fname;

    @Column(name="lname")
    private String lname;

    @Column(name="pass")
    private String pass;

    @Column(name="cpass")
    private String cpass;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name="usr_sur")
    //private Set<UserRole> roles;

    public void setId(int id) { this.id = id; }
    public int getId() { return id; }

    public void setUname(String uname) { this.userName = uname; }
    public String getUname() { return userName; }

    public void setEmail(String email) { this.email = email; }
    public String getEmail() { return email; }

    public void setFname(String fname) { this.fname = fname; }
    public String getFname() { return fname; }

    public void setLname(String lname) { this.lname = lname; }
    public String getLname() { return lname; }

    public void setPass(String pass) { this.pass = pass; }
    public String getPass() { return pass; }

    public void setCpass(String cpass) { this.cpass = cpass; }
    public String getCpass() { return cpass; }

    //public void setRoles(Set<UserRole> roles) { this.roles = roles; }
    //public Set<UserRole> getRoles() { return roles; }
}
