package com.logix.persistence.dto;

import com.logix.validation.ValidEmail;
import com.logix.validation.ValidPassword;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.util.List;

@ValidPassword
public class UserDto {
    //-----------------------------> User
    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    @NotEmpty
    private String pass;

    @NotNull
    @NotEmpty
    private String cpass;

    //----------------------------->Roles
    private List<String> roles;

    //----------------------------->Details
    private boolean enabled;

    private boolean acctNotExpired;

    private boolean credsNotExpired;

    private boolean acctNotLocked;

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPass() { return pass; }
    public void setPass(String pass) { this.pass = pass; }

    public String getCpass() { return cpass; }
    public void setCpass(String cpass) { this.cpass = cpass; }

    public List<String> getRoles(){ return roles; }
    public void setRoles(List<String> roles) { this.roles = roles; }

    public boolean getEnabled(){ return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public boolean getAcctNotExpired(){ return acctNotExpired; }
    public void setAcctNotExpired(boolean acctNotExpired){ this.acctNotExpired = acctNotExpired; }

    public boolean getCredsNotExpired(){ return credsNotExpired; }
    public void setCredsNotExpired(boolean credsNotExpired){ this.credsNotExpired=credsNotExpired; }

    public boolean getAcctNotLocked(){ return acctNotLocked; }
    public void setAcctNotLocked(boolean acctNotLocked){ this.acctNotLocked=acctNotLocked; }

    @Override
    public String toString(){
        StringBuffer sb = new StringBuffer();
        return sb.append("UserDto: FirstName-> ").append(getFirstName())
                .append("LastName-> ").append(getLastName())
                .append("Email-> ").append(getEmail())
                .append("Pass not included in this method...")
                .toString();
    }

    //TODO -> override hashcode and equals
}
