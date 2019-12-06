package com.epam.beans;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserShowTime {
	private Date dateSelected;
	private int timeSelected;
}
