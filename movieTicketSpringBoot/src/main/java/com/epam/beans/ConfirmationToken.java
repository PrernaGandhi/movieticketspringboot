package com.epam.beans;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ConfirmationToken {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "token_id")
	private long tokenid;

	@Column(name = "confirmation_token")
	private String confirmationTokenValue;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@OneToOne(targetEntity = Users.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private Users user;

	public ConfirmationToken() {
	}

	public ConfirmationToken(Users user) {
		this.user = user;
		createdDate = new Date();
		confirmationTokenValue = UUID.randomUUID().toString();
	}
}