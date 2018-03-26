package com.logix.data;

import com.logix.validation.ValidEmail;
import com.logix.validation.ValidPassword;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@ValidPassword
public class UserDto {
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

    @Override
    public String toString(){
        StringBuffer stringBuffer = new StringBuffer();
        return stringBuffer.append("UserDto: FirstName-> ").append(getFirstName())
                .append("LastName-> ").append(getLastName())
                .append("Email-> ").append(getEmail())
                .append("Pass not included in this method...")
                .toString();
    }

    //TODO -> override hashcode and equals
}
