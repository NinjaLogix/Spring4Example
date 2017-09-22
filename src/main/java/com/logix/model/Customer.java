package com.logix.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Hibernate model class using JPA annotations
 * @see <a href="www.mkyoung.com/hibernate/hibernate-named-query-examples/">MkYoung Hibernate</a>
 * @author bboyingt
 * @version 1.0.0
 */
@Entity
@Table(name="customer")
public class Customer implements Serializable { //TODO - research if (name="/*..*/") is needed when using the @Column annotation

	private static final long servialVersionUID = -7988799579036225137L;

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
