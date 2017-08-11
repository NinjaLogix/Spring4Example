package com.logix.model;

import java.io.Serializable;

/**
 * Customer pojo object definition.
 * @author Branden Boyington
 * @version ${version}
 * @since 1.0.0
 */
public class Customer implements Serializable {

	//TODO -- change access modifier to private and modify surrounding code
	public int 	custid;
	public String 	custname,
					city;
	
	public Customer() {
		
	}
	
	public Customer(int custid, String custname, String city) {
		this.custid = custid;
		this.custname = custname;
		this.city = city;
	}
	
	public int getId() {
		return custid;
	}
	
	public String getName() {
		return custname;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setId(int custid) {
		this.custid = custid;
	}
	
	public void setName(String custname) {
		this.custname = custname;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		
		Customer cust = (Customer) o;
		
		if (custid != cust.custid) return false;
		if (custname != null ? !custname.equals(cust.custname) : cust.custname != null) return false;
		if (city != null ? !city.equals(cust.city) : cust.city != null) return false;

		return true;
	}
	
	@Override
	public int hashCode() {
		int result = custid;
		result = 31 * result + (custname != null ? custname.hashCode() : 0) + (city != null ? city.hashCode() : 0);
		return result;
	}
	
	@Override
	public String toString() {
		return "User{" +
				"custid=" + custid +
				", custname='" + custname + 
				", city='" + city + '\'' +
				'}';
	}

}
