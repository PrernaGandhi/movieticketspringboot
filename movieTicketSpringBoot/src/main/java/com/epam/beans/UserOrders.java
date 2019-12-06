package com.epam.beans;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Table(name = "user_orders")
@Entity
@Getter
@Setter
public class UserOrders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int orderId;
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_id")
	private User user;
	@Column(name = "username")
	private String userName;
	@Column(name = "location_name")
	private String locationName;
	@Column(name = "movie_name")
	private String movieName;
	@Column(name = "theater_name")
	private String theaterName;
	@Transient
	private int timingsId;
	@ManyToOne(targetEntity = Timings.class)
	@JoinColumn(name = "timings")
	private Timings timings;
	@Column(name = "seats_booked")
	private String seatsBooked;
	@Column(name = "total_price")
	private double totalPrice;
	@Column(name = "date_of_purchase")
	private Date dateOfPurchase;
}
