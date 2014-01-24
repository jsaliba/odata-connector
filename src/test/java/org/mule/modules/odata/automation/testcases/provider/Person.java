package org.mule.modules.odata.automation.testcases.provider;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang.builder.EqualsBuilder;


@Entity
public class Person {

	@Id
	private String id;
	private String name;
	private String surname;
	
	public Person() {}
	
	public Person(String id, String name, String surname) {
		this.id = id;
		this.name = name;
		this.surname = surname;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	@Override
	public String toString() {
		return "Id: "+id+"\nName: "+name+"\nSurname: "+surname;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Person) {
			return EqualsBuilder.reflectionEquals(this, obj);
		}
		return false;
	}
	
}
