package com.epam.validator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.beans.Timings;
import com.epam.repository.TimingRepository;
import com.epam.utils.RegexMatcher;

@Service
public class TimeSelectedValidatorService implements ValidatorService {
	private static final String HYPHEN = "-";
	@Autowired
	TimingRepository timingRepository;
	@Autowired
	RegexMatcher regexMatcher;

	@Override
	public boolean validate(String inputValue) {
		boolean isValid = false;
		if (inputValue != null && inputValue.contains(HYPHEN)) {
			String timingId = inputValue.split(HYPHEN)[0];
			String timing = inputValue.split(HYPHEN)[1];
			if (regexMatcher.matchPattern("([0-9]+)", timingId)) {
				Timings time = timingRepository.findById(Integer.parseInt(timingId));
				String name = time != null ? time.getTiming() : "";
				if (name.equalsIgnoreCase(timing)) {
					isValid = true;
				}
			}
		}
		return isValid;
	}
}
