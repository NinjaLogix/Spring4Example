package com.logix.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.UniqueConstraint;
import javax.persistence.GeneratedValue;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="user", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class User implements Serializable{
    public static final long serialVersion = 1L;

    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="id", unique = true, nullable = false)
    private String id;

    @Column(name="uname")
    private String userName;

    @Column(name="email", unique=true, length=40)
    private String email;

    @Column(name="fname")
    private String fname;

    @Column(name="lname")
    private String lname;

    @Column(name="pass")
    private String pass;

    @Column(name="cpass")
    private String cpass;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private Set<UserRole> roles;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Details details;

    public void setId(String id) { this.id = id; }
    public String getId() { return id; }

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

    public void setRoles(Set<UserRole> roles) { this.roles = roles; }
    public Set<UserRole> getRoles() { return roles; }

    public void setDetails(Details details){
        if(details == null){
            if(this.details != null){
                this.details.setUser(null);
            }
        } else {
            details.setUser(this);
        }
        this.details = details;
    }
    public Details getDetails() { return details; }
}
