package com.abmv.angular.attack.entities.sql;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class AppUser {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String contact;
	private String username;
	private String password;
	private String emailId;
	
	public AppUser() {
	}
	
	public AppUser(String name){
		this.name=name;
	}
}
