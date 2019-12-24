package com.epam.validator.service;

import java.sql.Date;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.utils.RegexMatcher;

@Service
public class DateSelectedValidatorService implements ValidatorService {
	@Autowired
	RegexMatcher regexMatcher;

	@Override
	public boolean validate(String inputValue) {
		return false;
	}

	public boolean validate(Date dateSelected) {
		boolean isValid = false;
		Calendar calender = Calendar.getInstance();
		calender.add(Calendar.DATE, 7);
		Calendar today = Calendar.getInstance();
		today.add(Calendar.DATE, -1);
		if (dateSelected.after(Calendar.getInstance().getTime()) && dateSelected.before(calender.getTime())) {
			isValid = true;
		}
		return isValid;
	}

}
