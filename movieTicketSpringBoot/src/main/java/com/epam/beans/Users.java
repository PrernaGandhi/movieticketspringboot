package com.epam.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Entity
@Table(name = "user")
@Getter
@Setter
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	int userId;
	private String email;
	private String password;
	private String username;
	private String firstName;
	private String lastName;
	private int age;
	private String gender;
}
