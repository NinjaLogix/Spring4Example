package com.logix.model;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;

import java.io.Serializable;

/**
 * Hibernate model class using JPA annotations.
 * @see <a href="www.mkyoung.com/hibernate/hibernate-named-query-examples/">MkYoung Hibernate</a>
 * @author bboyingt
 * @version 1.0.0
 *
 * Added named queries here, but they can be added anywhere really. One good practice is to place them
 * in package-info.java since this is outside of the classpath.
 */

@NamedQueries({
		@NamedQuery(
				name="Customer.findByName",
				query="from Customer c where c.custname = :name"),
		@NamedQuery(
				name="Customer.findAll",
				query="from Customer c"),
		@NamedQuery(
				name="Customer.findById",
				query="from Customer c where c.custid = :id")
})

@Entity
@Table(name="customer")
public class Customer implements Serializable {

	private static final long servialVersionUID = 1L;

	@Id
	@Column
	public int  custid;

	@Column
	public String   custname;

	@Column
	public String   city;

	public Customer(){
	}

	public int getCustid(){
		return custid;
	}

	public void setCustid(int custid){
		this.custid=custid;
	}

	public String getCustname(){
		return custname;
	}

	public void setCustname(String custname){
		this.custname=custname;
	}

	public String getCity(){
		return city;
	}

	public void setCity(String city){
		this.city = city;
	}

	@Override
	public String toString(){
		return "Customer--> custid: "+custid+", name: "+custname+", city: "+city;
	}
}
