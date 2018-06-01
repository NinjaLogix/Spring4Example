package com.logix.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.UniqueConstraint;
import javax.persistence.GeneratedValue;

import java.io.Serializable;

@Entity
@Table(name="user_details", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Details implements Serializable{
    public static final long serialVersion = 1L;

    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name="id", unique = true, nullable = false)
    private String id;

    @Column(name="acct_enbl")
    private boolean enabled;

    @Column(name="acct_non_exp")
    private boolean acctNotExpired;

    @Column(name="creds_non_exp")
    private boolean credsNotExpired;

    @Column(name="acct_non_lked")
    private boolean acctNotLocked;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void setId(String id){ this.id = id; }
    public String getId(){ return id; }

    public void setUser(User user){ this.user = user; }
    public User getUser(){ return user; }

    public void setEnabled(boolean enabled){ this.enabled = enabled; }
    public boolean getEnabled(){ return enabled; }

    public void setAcctNotExpired(boolean acctNotExpired){ this.acctNotExpired = acctNotExpired; }
    public boolean getAcctNotExpired(){ return acctNotExpired; }

    public void setCredsNotExpired(boolean credsNotExpired){ this.credsNotExpired = credsNotExpired; }
    public boolean getCredsNotExpired(){ return credsNotExpired; }

    public void setAcctNotLocked(boolean acctNotLocked){ this.acctNotLocked = acctNotLocked; }
    public boolean getAcctNotLocked(){ return acctNotLocked; }
}
